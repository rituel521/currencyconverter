package com.bhargavms.currencyconverter

// this level of decoupling allows for easy A/B experimentation because we can plug in different
// styles of each of the views used in the screen at run time very easily.
class MainScreenActivityDelegate(
    private val currencySelectorView: CurrencySelectorView,
    private val currencyPricePriceEditorView: CurrencyPriceEditorView,
    private val loadingView: LoaderView,
    private val liveQuotesView: LiveQuotesView,
    private val mainScreenPresenter: MainScreenPresenter
) : MainScreen, MainActivityDelegate {
    override fun showNetworkError() {
        currencyPricePriceEditorView.setError()
    }

    override fun showLiveRatesForSupporterCurrencies(liveQuoteViewModel: List<MainScreen.LiveQuoteViewModel>) {
        liveQuotesView.showQuotes(liveQuoteViewModel)
    }

    override fun hideLoader() {
        loadingView.hide()
    }

    override fun showLoader() {
        loadingView.show()
    }

    override fun showCurrencies(currencyViewModel: List<MainScreen.CurrencyViewModel>) {
        currencySelectorView.setCurrencies(currencyViewModel)
    }

    override fun destroy() {
        mainScreenPresenter.onDestroyScreen()
    }

    override fun pnActivityReady() {
        mainScreenPresenter.onCreateScreen(this)
        currencyPricePriceEditorView.listen {
            mainScreenPresenter.onSetNewPrice(it, currencySelectorView.getSelectedCurrency())
        }
    }
}
