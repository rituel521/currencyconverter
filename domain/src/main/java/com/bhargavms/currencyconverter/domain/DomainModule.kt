package com.bhargavms.currencyconverter.domain

import com.bhargavms.currencyconverter.domain.currency.ShowSupportedCurrencies
import com.bhargavms.currencyconverter.domain.currency.ShowSupportedCurrenciesImpl
import com.bhargavms.currencyconverter.domain.currency.SupportedCurrenciesRepo
import com.bhargavms.currencyconverter.domain.quotes.LiveQuotesRepo
import com.bhargavms.currencyconverter.domain.quotes.ShowLiveQuotes
import com.bhargavms.currencyconverter.domain.quotes.ShowLiveQuotesImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideShowSupportedCurrencies(
        supportedCurrenciesRepo: SupportedCurrenciesRepo
    ): ShowSupportedCurrencies {
        return ShowSupportedCurrenciesImpl(supportedCurrenciesRepo)
    }

    @Provides
    fun provideShowLiveQuotes(
        supportedCurrenciesRepo: SupportedCurrenciesRepo,
        liveQuotesRepo: LiveQuotesRepo
    ): ShowLiveQuotes {
        return ShowLiveQuotesImpl(liveQuotesRepo, supportedCurrenciesRepo)
    }
}
