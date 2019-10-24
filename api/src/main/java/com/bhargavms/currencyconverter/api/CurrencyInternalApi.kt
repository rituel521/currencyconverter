package com.bhargavms.currencyconverter.api

import retrofit2.http.GET

internal interface CurrencyInternalApi {

    companion object {
        private const val apiKey = "0626fd25b84cf06c6f550c1ffe46a1d2"
    }

    @GET("api//live?access_key=$apiKey")
    suspend fun liveRates(
        // FREE PLAN DOESNT SUPPORT SOURCE SWITTCHING
//        @Query("source") source: String
    ): LiveRatesResponse

    @GET("api//list?access_key=$apiKey")
    suspend fun listSupportedCurrencies(): SupportedCurrenciesResponse
}
