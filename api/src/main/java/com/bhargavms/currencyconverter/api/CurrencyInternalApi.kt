package com.bhargavms.currencyconverter.api

internal interface CurrencyInternalApi {
    suspend fun liveRates(source: String): LiveRatesResponse

    suspend fun listSupportedCurrencies(): SupportedCurrenciesResponse
}
