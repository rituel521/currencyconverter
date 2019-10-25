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
