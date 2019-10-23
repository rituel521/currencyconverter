package com.bhargavms.currencyconverter.data

import com.bhargavms.currencyconverter.api.CurrencyApiService
import com.bhargavms.currencyconverter.api.NetworkApiModule
import com.bhargavms.currencyconverter.domain.currency.SupportedCurrenciesRepo
import com.bhargavms.currencyconverter.domain.quotes.LiveQuotesRepo
import com.bhargavms.currencyconverter.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkApiModule::class])
class DataModule {
    @Provides
    @AppScope
    internal fun provideCurrencyRepository(
        currencyApiService: CurrencyApiService,
        currenciesCacheStorage: CurrenciesCacheStorage
    ): CurrencyRepository {
        return CurrencyRepository(
            currencyApiService, currenciesCacheStorage
        )
    }

    @Provides
    internal fun provideLiveQuotesRepo(
        currencyRepository: CurrencyRepository
    ): LiveQuotesRepo = currencyRepository

    @Provides
    internal fun provideSupportedCurrenciesRepo(
        currencyRepository: CurrencyRepository
    ): SupportedCurrenciesRepo = currencyRepository
}
