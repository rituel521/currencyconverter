package com.bhargavms.currencyconverter.domain.quotes

interface LiveQuotesRepo {
    suspend fun getLiveQuotesForCurrency(source: String): List<Quote>
}

data class Quote(
    val currencyid: String,
    val valueConverter: ValueConverter
) {

    data class ValueConverter(private val value: Double) {
        operator fun invoke(factor: Double) = value * factor
    }
}
