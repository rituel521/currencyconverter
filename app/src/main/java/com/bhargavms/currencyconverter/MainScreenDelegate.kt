package com.bhargavms.currencyconverter

import com.bhargavms.currencyconverter.domain.currency.ShowSupportedCurrencies
import com.bhargavms.currencyconverter.domain.invoke
import com.bhargavms.currencyconverter.domain.quotes.ShowLiveQuotes
import com.bhargavms.currencyconverter.domain.quotes.ShowQuotesFor
import kotlinx.coroutines.Job
import java.lang.ref.WeakReference
import kotlin.math.round

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
}

class MainScreenPresenter(
    private val showSupportedCurrencies: ShowSupportedCurrencies,
    private val showLiveQuotes: ShowLiveQuotes
) {
    private var screen: WeakReference<MainScreen?> = WeakReference(null)

    private val jobs: MutableSet<Job> = mutableSetOf()

    private fun addJob(job: Job) {
        jobs.add(job)
    }

    fun onDestroyScreen() {
        jobs.forEach { if (it.isActive) it.cancel() }
    }

    private inline fun ifScreenAttachedDo(doFunc: MainScreen.() -> Unit) {
        screen.get()?.apply(doFunc)
    }

    fun onCreateScreen(screen: MainScreen) {
        this.screen = WeakReference(screen)
        screen.showLoader()
        loadCurrencies()
    }

    private fun loadCurrencies() {
        addJob(showSupportedCurrencies {
            ifScreenAttachedDo {
                hideLoader()
                showCurrencies(it.values.map {
                    MainScreen.CurrencyViewModel(it.id, it.name)
                })
            }
        })
    }

    fun onSetNewPrice(price: CharSequence, currency: CharSequence) {
        ifScreenAttachedDo { showLoader() }
        try {
            showLiveQuotes(ShowQuotesFor(currency.toString(), price.toString().toDouble())) {
                ifScreenAttachedDo {
                    hideLoader()
                    showLiveRatesForSupporterCurrencies(it.map {
                        MainScreen.LiveQuoteViewModel(
                            it.value.round(2),
                            it.currency.name, it.currency.id
                        )
                    })
                }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return round(this * multiplier) / multiplier
    }
}


interface MainScreen {
    fun showCurrencies(currencyViewModel: List<CurrencyViewModel>)
    fun showLiveRatesForSupporterCurrencies(liveQuoteViewModel: List<LiveQuoteViewModel>)
    fun showLoader()
    fun hideLoader()

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

interface LoaderView {
    fun show()

    fun hide()
}

interface LiveQuotesView {
    fun showQuotes(quotes: List<MainScreen.LiveQuoteViewModel>)
}

// this level of decoupling allows for easy A/B experimentation because we can plug in different
// styles of each of the views used in the screen at run time very easily.
class MainScreenActivityDelegate(
    private val currencySelectorView: CurrencySelectorView,
    private val currencyPricePriceEditorView: CurrencyPriceEditorView,
    private val loadingView: LoaderView,
    private val liveQuotesView: LiveQuotesView,
    private val mainScreenPresenter: MainScreenPresenter
) : MainScreen, MainActivityDelegate {
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
            try {
                mainScreenPresenter.onSetNewPrice(it, currencySelectorView.getSelectedCurrency())
            } catch (ex: java.lang.Exception) {
                ex.printStackTrace()
            }
        }
    }
}
