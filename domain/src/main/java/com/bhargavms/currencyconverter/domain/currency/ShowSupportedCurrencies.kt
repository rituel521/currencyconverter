package com.bhargavms.currencyconverter.domain.currency

import com.bhargavms.currencyconverter.domain.ShowUseCase
import com.bhargavms.currencyconverter.domain.invoke
import com.bhargavms.currencyconverter.domain.onIO
import kotlinx.coroutines.*

interface ShowSupportedCurrencies : ShowUseCase<Unit, Map<String, Currency>>

internal class ShowSupportedCurrenciesImpl(
    private val supportedCurrenciesRepo: SupportedCurrenciesRepo
) : ShowSupportedCurrencies {
    override fun invoke(params: Unit, outputBlock: (Map<String, Currency>) -> Unit): Job =
        onIO {
            supportedCurrenciesRepo.getSupportedCurrencies().let(outputBlock)
        }
}
