package com.bhargavms.currencyconverter.domain.currency

import com.bhargavms.currencyconverter.domain.ShowUseCase
import com.bhargavms.currencyconverter.domain.launch
import com.bhargavms.currencyconverter.domain.onIO
import kotlinx.coroutines.Job

interface ShowSupportedCurrencies : ShowUseCase<Unit, Map<String, Currency>>

internal class ShowSupportedCurrenciesImpl(
    private val supportedCurrenciesRepo: SupportedCurrenciesRepo
) : ShowSupportedCurrencies {
    override fun invoke(params: Unit, outputBlock: (Map<String, Currency>) -> Unit): Job = launch {
        val result = onIO { supportedCurrenciesRepo.getSupportedCurrencies() }
        outputBlock(result)
    }
}
