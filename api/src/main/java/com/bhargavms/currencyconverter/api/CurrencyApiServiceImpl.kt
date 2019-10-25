package com.bhargavms.currencyconverter.api

internal class CurrencyApiServiceImpl(
    private val currencyInternalApiImpl: CurrencyInternalApi
) : CurrencyApiService {
    override suspend fun liveRates(source: String): Quotes {
        // FREE PLAN DOESNT SUPPORT SOURCE SWITCHING :(
        return currencyInternalApiImpl.liveRates().takeIf { it.success }?.let { response ->
            val quotes = response.quotes
            quotes.keySet().mapNotNull { key ->
                try {
                    val targetCurrencyId: String = key.substring(source.length)
                    Quote(
                        targetCurrencyId,
                        quotes[key].asDouble
                    )
                } catch (ex: Exception) {
                    null
                }
            }
        }?.let { Quotes(it) } ?: throw NetworkException()
    }

    override suspend fun listSupportedCurrencies(): Currencies {
        return currencyInternalApiImpl.listSupportedCurrencies().takeIf { it.success }?.let { response ->
            val currencies = response.currencies
            currencies.keySet().mapNotNull {
                try {
                    Currency(
                        it,
                        currencies[it].asString
                    )
                } catch (ex: Exception) {
                    null
                }
            }
        }?.let { Currencies(it) } ?: throw NetworkException()
    }
}

