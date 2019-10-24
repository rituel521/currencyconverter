package com.bhargavms.currencyconverter.data

import com.bhargavms.currencyconverter.api.Currencies
import com.bhargavms.currencyconverter.api.CurrencyApiService
import com.bhargavms.currencyconverter.api.Quotes
import com.bhargavms.currencyconverter.domain.currency.Currency
import com.bhargavms.currencyconverter.domain.currency.SupportedCurrenciesRepo
import com.bhargavms.currencyconverter.domain.quotes.LiveQuotesRepo
import com.bhargavms.currencyconverter.domain.quotes.Quote
import java.util.concurrent.TimeUnit

internal class CurrencyRepository(
    private val currencyApiService: CurrencyApiService,
    private val currenciesCacheStorage: CurrenciesCacheStorage
) : LiveQuotesRepo, SupportedCurrenciesRepo {

    override suspend fun getSupportedCurrencies(): Map<String, Currency> {
        val cache = currenciesCacheStorage.getSupportedCurrencies()
        return if (cache == null || now isAfter30MinutesFrom cache.storeTime) {
            getSupportedCurrenciesFromNetwork()
                .also { currenciesCacheStorage.writeSupportedCurrencies(it) }
                .currencies
                .map { Currency(it.id, it.name) }.associateBy { it.id }
        } else {
            cache.data
                .currencies
                .map { Currency(it.id, it.name) }.associateBy { it.id }
        }
    }

    override suspend fun getLiveQuotesForCurrency(source: String): List<Quote> {
        val cache = currenciesCacheStorage.getLiveQuotesForCurrency(source)
        return if (cache == null || now isAfter30MinutesFrom cache.storeTime) {
            getLiveQuotesFromNetwork(source)
                .also { currenciesCacheStorage.writeLiveQuotesForCurrency(source, it) }
                .quotes.map { Quote(it.id, Quote.ValueConverter(it.conversionRate)) }
        } else {
            cache.data.quotes.map { Quote(it.id, Quote.ValueConverter(it.conversionRate)) }
        }
    }

    private val now get() = System.currentTimeMillis()

    private infix fun Long.isAfter30MinutesFrom(fromTime: Long): Boolean = (this - fromTime) >= CACHE_TIME_LIMIT

    private suspend fun getLiveQuotesFromNetwork(source: String): Quotes = currencyApiService.liveRates(source)

    private suspend fun getSupportedCurrenciesFromNetwork(): Currencies = currencyApiService.listSupportedCurrencies()

    companion object {
        private val CACHE_TIME_LIMIT = TimeUnit.MINUTES.toMillis(30)
    }
}
