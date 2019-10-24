package com.bhargavms.currencyconverter.api

import com.bhargavms.currencyconverter.scopes.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class NetworkApiModule {
    @Provides
    @AppScope
    internal fun provideInternalApi(): CurrencyInternalApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilayer.net/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrencyInternalApi::class.java)
    }

    @Provides
    internal fun provideCurrencyApiService(
        currencyInternalApi: CurrencyInternalApi
    ): CurrencyApiService = CurrencyApiServiceImpl(currencyInternalApi)
}
