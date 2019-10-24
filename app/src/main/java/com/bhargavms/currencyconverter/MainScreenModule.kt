package com.bhargavms.currencyconverter

import com.bhargavms.currencyconverter.domain.currency.ShowSupportedCurrencies
import com.bhargavms.currencyconverter.domain.quotes.ShowLiveQuotes
import com.bhargavms.currencyconverter.scopes.ScreenScope
import dagger.Module
import dagger.Provides

@Module
class MainScreenModule(
    val activity: MainActivity
) {
    @Provides
    fun provideDelegate(
        mainScreenPresenter: MainScreenPresenter
    ): MainActivityDelegate = MainScreenActivityDelegate(
        activity.currencySelectorView,
        activity.currencyPriceEditorView,
        activity.loaderView,
        activity.liveQuotesView,
        mainScreenPresenter
    )

    @Provides
    @ScreenScope
    fun providePresenter(
        showSupportedCurrencies: ShowSupportedCurrencies,
        showLiveQuotes: ShowLiveQuotes
    ): MainScreenPresenter = MainScreenPresenter(
        showSupportedCurrencies, showLiveQuotes
    )
}
