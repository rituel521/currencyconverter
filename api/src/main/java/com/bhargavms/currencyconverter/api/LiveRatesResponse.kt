package com.bhargavms.currencyconverter.api

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

internal data class LiveRatesResponse(
    @SerializedName("success")
    override val success: Boolean = false,
    @SerializedName("timestamp")
    val timestamp: Long?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("quotes")
    val quotes: JsonObject
): BaseNetworkResponse()
