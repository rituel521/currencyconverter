package com.bhargavms.currencyconverter.api

interface CurrencyApiService {
    suspend fun liveRates(source: String): Quotes

    suspend fun listSupportedCurrencies(): Currencies
}

data class Currencies(
    val currencies: List<Currency>
)

data class Quotes(
    val quotes: List<Quote>
)

data class Currency(
    val id: String,
    val name: String
)

internal class CurrencyApiServiceImpl(
    private val currencyInternalApiImpl: CurrencyInternalApi
) : CurrencyApiService {
    override suspend fun liveRates(source: String): Quotes {
        return currencyInternalApiImpl.liveRates(source).takeIf { it.success }?.let { response ->
            val quotes = response.quotes
            quotes.keySet().mapNotNull { key ->
                try {
                    val targetCurrencyId: String = key.substring(source.length)
                    Quote(
                        targetCurrencyId,
                        quotes[key].asDouble
                    )
                } catch (ex: Exception) {
                    null
                }
            }
        }?.let { Quotes(it) } ?: throw NetworkException()
    }

    override suspend fun listSupportedCurrencies(): Currencies {
        return currencyInternalApiImpl.listSupportedCurrencies().takeIf { it.success }?.let { response ->
            val currencies = response.currencies
            currencies.keySet().mapNotNull {
                try {
                    Currency(
                        it,
                        currencies[it].asString
                    )
                } catch (ex: Exception) {
                    null
                }
            }
        }?.let { Currencies(it) } ?: throw NetworkException()
    }
}
