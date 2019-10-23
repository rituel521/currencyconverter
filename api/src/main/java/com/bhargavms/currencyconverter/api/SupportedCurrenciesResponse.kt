package com.bhargavms.currencyconverter.api

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

internal data class SupportedCurrenciesResponse(
    @SerializedName("success")
    override val success: Boolean,
    @SerializedName("currencies")
    val currencies: JsonObject,
    val timestamp: Long
): BaseNetworkResponse()
