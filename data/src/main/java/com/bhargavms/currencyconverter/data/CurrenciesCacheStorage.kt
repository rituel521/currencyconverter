package com.bhargavms.currencyconverter.data

import android.content.Context
import android.content.SharedPreferences
import com.bhargavms.currencyconverter.api.Currencies
import com.bhargavms.currencyconverter.api.Currency
import com.bhargavms.currencyconverter.api.Quote
import com.bhargavms.currencyconverter.api.Quotes
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.lang.Exception

internal class CurrenciesCacheStorage(context: Context) {
    private val preferences: SharedPreferences by lazy {
        context.applicationContext.getSharedPreferences("currencies_storage", Context.MODE_PRIVATE)
    }
    private val gson: Gson = GsonBuilder().serializeNulls().create()

    suspend fun getLiveQuotesForCurrency(source: String): Cache<Quotes>? {
        return preferences.getString(source, null)?.let {
            try {
                gson.fromJson(it, QuotesDataModel::class.java)?.let {
                    Cache(it.storeTime, Quotes(it.quotes.map {
                        Quote(it.currencyid, it.conversionRate)
                    }))
                }
            } catch (ex: Exception) {
                null
            }
        }
    }

    suspend fun writeLiveQuotesForCurrency(sourceCurrency: String, data: Quotes) {
        preferences.edit {
            putString(
                sourceCurrency, gson.toJson(
                    QuotesDataModel(
                        System.currentTimeMillis(),
                        data.quotes.map {
                            QuoteDataModel(it.id, it.conversionRate)
                        })
                )
            )
        }
    }

    suspend fun getSupportedCurrencies(): Cache<Currencies>? =
        preferences.getString(SUPPORTED_CURRENCIES, null)?.let {
            gson.fromJson(it, CurrenciesDataModel::class.java)
                ?.let {
                    Cache(
                        it.storeTime,
                        Currencies(it.currencies.map { Currency(it.id, it.name) })
                    )
                }
        }

    suspend fun writeSupportedCurrencies(currencies: Currencies) {
        preferences.edit {
            putString(
                SUPPORTED_CURRENCIES, gson.toJson(
                    CurrenciesDataModel(
                        System.currentTimeMillis(),
                        currencies.currencies.map { CurrencyDataModel(it.id, it.name) }
                    )
                )
            )
        }
    }

    companion object {
        private const val SUPPORTED_CURRENCIES = "currencies_storage.supported"
    }
}

inline fun SharedPreferences.edit(function: SharedPreferences.Editor.() -> Unit) {
    this.edit().apply(function).commit()
}

data class Cache<T>(val storeTime: Long, val data: T)

private data class CurrenciesDataModel(
    @SerializedName("storeTime")
    val storeTime: Long,
    @SerializedName("currencies")
    val currencies: List<CurrencyDataModel>
)

private data class CurrencyDataModel(
    @SerializedName("currencyId")
    val id: String,
    @SerializedName("currencyName")
    val name: String
)

private data class QuotesDataModel(
    @SerializedName("storeTime")
    val storeTime: Long,
    @SerializedName("quotes")
    val quotes: List<QuoteDataModel>
)

private data class QuoteDataModel(
    @SerializedName("currencyid")
    val currencyid: String,
    @SerializedName("valueConverter")
    val conversionRate: Double
)
