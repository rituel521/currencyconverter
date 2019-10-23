package com.bhargavms.currencyconverter.api

import dagger.Module
import dagger.Provides

@Module
class NetworkApiModule {
    @Provides
    internal fun provideInternalApi(): CurrencyInternalApi {
        return CurrencyInternalApiImpl()
    }

    @Provides
    internal fun provideCurrencyApiService(
        currencyInternalApi: CurrencyInternalApi
    ): CurrencyApiService {
        return CurrencyApiServiceImpl(currencyInternalApi)
    }
}
