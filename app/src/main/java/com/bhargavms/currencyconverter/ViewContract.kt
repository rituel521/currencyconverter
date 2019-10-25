package com.bhargavms.currencyconverter

interface MainActivityDelegate {
    fun pnActivityReady()

    fun destroy()
}

interface CurrencySelectorView {
    fun setCurrencies(currencyViewModel: List<MainScreen.CurrencyViewModel>)
    fun getSelectedCurrency(): CharSequence
}

interface CurrencyPriceEditorView {
    fun listen(
        onDoneEditing: (CharSequence) -> Unit
    )
    fun setError()
}

interface LoaderView {
    fun show()

    fun hide()
}

interface LiveQuotesView {
    fun showQuotes(quotes: List<MainScreen.LiveQuoteViewModel>)
}

interface MainScreen {
    fun showCurrencies(currencyViewModel: List<CurrencyViewModel>)
    fun showLiveRatesForSupporterCurrencies(liveQuoteViewModel: List<LiveQuoteViewModel>)
    fun showLoader()
    fun hideLoader()
    fun showNetworkError()

    data class CurrencyViewModel(
        val abrevation: String,
        val fullName: String
    ) {
        override fun toString(): String {
            return abrevation
        }
    }

    data class LiveQuoteViewModel(
        val convertedRate: Double,
        val currencyFullName: String,
        val currencyAbrievation: String
    )
}
