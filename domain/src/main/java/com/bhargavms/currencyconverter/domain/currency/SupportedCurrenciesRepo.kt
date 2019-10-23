package com.bhargavms.currencyconverter.domain.currency

interface SupportedCurrenciesRepo {
    suspend fun getSupportedCurrencies(): Map<String, Currency>
}

data class Currency(
    val id: String,
    val name: String
)
