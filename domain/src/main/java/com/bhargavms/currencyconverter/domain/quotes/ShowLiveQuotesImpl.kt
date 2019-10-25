package com.bhargavms.currencyconverter.domain.quotes

import com.bhargavms.currencyconverter.domain.currency.Currency
import com.bhargavms.currencyconverter.domain.currency.SupportedCurrenciesRepo
import com.bhargavms.currencyconverter.domain.launch
import com.bhargavms.currencyconverter.domain.onIO
import kotlinx.coroutines.Job

internal class ShowLiveQuotesImpl(
    private val liveQuotesRepo: LiveQuotesRepo,
    private val supportedCurrenciesRepo: SupportedCurrenciesRepo
) : ShowLiveQuotes {
    override fun invoke(
        params: ShowQuotesFor,
        outputBlock: (List<QuoteForCurrency>) -> Unit,
        errorBLock: () -> Unit
    ): Job = launch {
        try {
            val result = onIO {
                val supportedCurrencies: Map<String, Currency> = supportedCurrenciesRepo.getSupportedCurrencies()
                liveQuotesRepo.getLiveQuotesForCurrency(params.currencyId).let {
                    it.mapNotNull {
                        supportedCurrencies.get(it.currencyid)?.let { currency ->
                            QuoteForCurrency(
                                currency,
                                it.valueConverter(params.price)
                            )
                        }
                    }
                }
            }
            outputBlock(result)
        } catch (ex: Exception) {
            errorBLock()
        }
    }
}
