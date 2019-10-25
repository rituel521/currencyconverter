package com.bhargavms.currencyconverter

import com.bhargavms.currencyconverter.domain.currency.ShowSupportedCurrencies
import com.bhargavms.currencyconverter.domain.invoke
import com.bhargavms.currencyconverter.domain.quotes.ShowLiveQuotes
import com.bhargavms.currencyconverter.domain.quotes.ShowQuotesFor
import kotlinx.coroutines.Job
import java.lang.ref.WeakReference

class MainScreenPresenter(
    private val showSupportedCurrencies: ShowSupportedCurrencies,
    private val showLiveQuotes: ShowLiveQuotes
) {
    private var screen: WeakReference<MainScreen?> = WeakReference(null)

    private val jobs: MutableSet<Job> = mutableSetOf()

    private inline fun screenScope(jobRunner: () -> Job) {
        jobs.add(jobRunner())
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
        screenScope {
            showSupportedCurrencies({
                ifScreenAttachedDo {
                    hideLoader()
                    showCurrencies(it.values.map {
                        MainScreen.CurrencyViewModel(it.id, it.name)
                    })
                }
            }, {
                ifScreenAttachedDo {
                    hideLoader()
                    showNetworkError()
                }
            })
        }
    }

    fun onSetNewPrice(price: CharSequence, currency: CharSequence) {
        if (price.isBlank() || currency.isBlank()) return
        ifScreenAttachedDo { showLoader() }
        screenScope {
            showLiveQuotes(ShowQuotesFor(currency.toString(), price.toString().toDouble()), {
                ifScreenAttachedDo {
                    hideLoader()
                    showLiveRatesForSupporterCurrencies(it.map {
                        MainScreen.LiveQuoteViewModel(
                            it.value.round(2),
                            it.currency.name, it.currency.id
                        )
                    })
                }
            }, {
                ifScreenAttachedDo {
                    hideLoader()
                    showNetworkError()
                }
            })
        }
    }

    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }
}

