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
