package com.bhargavms.currencyconverter.domain.quotes

import com.bhargavms.currencyconverter.domain.ShowUseCase
import com.bhargavms.currencyconverter.domain.currency.Currency
import com.bhargavms.currencyconverter.domain.currency.SupportedCurrenciesRepo
import com.bhargavms.currencyconverter.domain.launch
import com.bhargavms.currencyconverter.domain.onIO
import kotlinx.coroutines.Job

interface ShowLiveQuotes : ShowUseCase<ShowQuotesFor, List<QuoteForCurrency>>

data class QuoteForCurrency(
    val currency: Currency,
    val value: Double
)

data class ShowQuotesFor(
    val currencyId: String,
    val price: Double
)

internal class ShowLiveQuotesImpl(
    private val liveQuotesRepo: LiveQuotesRepo,
    private val supportedCurrenciesRepo: SupportedCurrenciesRepo
) : ShowLiveQuotes {
    override fun invoke(
        params: ShowQuotesFor,
        outputBlock: (List<QuoteForCurrency>) -> Unit
    ): Job = launch {
        val result = onIO {
            liveQuotesRepo.getLiveQuotesForCurrency(params.currencyId).let {
                val supportedCurrencies: Map<String, Currency> =
                    supportedCurrenciesRepo.getSupportedCurrencies()
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
    }
}
