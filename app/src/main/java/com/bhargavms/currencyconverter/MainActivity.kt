package com.bhargavms.currencyconverter

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), DIActivity<MainActivity> {
    override fun createComponent(applicationComponent: ApplicationComponent): MainScreenComponent {
        return applicationComponent.mainScreenComponent(MainScreenModule(this))
    }

    val currencySelectorView: CurrencySelectorView = CurrencySelectorViewImpl()
    val currencyPriceEditorView: CurrencyPriceEditorView = CurrencyPriceEditorViewImpl()
    val loaderView: LoaderView = object : LoaderView {
        override fun show() {
            loaderOverlay.visibility = View.VISIBLE
        }

        override fun hide() {
            loaderOverlay.visibility = View.GONE
        }

    }
    val liveQuotesView: LiveQuotesView by lazy {
        LiveQuotesRecyclerView()
    }
    @Inject
    lateinit var delegate: MainActivityDelegate

    @LayoutRes
    override fun getContentViewLayoutId(): Int = R.layout.activity_main

    override fun onReady(savedInstanceState: Bundle?) {
        delegate.pnActivityReady()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            delegate.destroy()
        }
    }

    private inner class CurrencyPriceEditorViewImpl : CurrencyPriceEditorView {
        override fun setError() {
            setPriceView.setError("Network Error!")
        }

        override fun listen(onDoneEditing: (CharSequence) -> Unit) {
            setPriceView.setOnEditorActionListener(object: TextView.OnEditorActionListener {
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        onDoneEditing(setPriceView.text)
                        return true
                    }
                    return false
                }
            })
        }
    }

    private inner class LiveQuotesRecyclerView : LiveQuotesView {
        private val dataSource = LiveQuoteDataSource()

        init {
            quotesRecyclerView.adapter = LiveQuotesRecyclerViewAdapter(dataSource)
            quotesRecyclerView.layoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)
            quotesRecyclerView.addItemDecoration(MarginItemDecoration(
                resources.getDimensionPixelOffset(R.dimen.cell_margin)
            ))
        }
        override fun showQuotes(quotes: List<MainScreen.LiveQuoteViewModel>) {
            dataSource.quotes = quotes.toList()
        }

        private inner class LiveQuoteDataSource : LiveQuotesRecyclerViewAdapter.DataSource {
            var quotes: List<MainScreen.LiveQuoteViewModel> = listOf()
                set(value) {
                    field = value
                    changeListener()
                }
            private var changeListener: () -> Unit = {}

            override val count: Int
                get() = quotes.count()

            override fun observeChange(function: () -> Unit) {
                changeListener = function
            }

            override fun get(position: Int): MainScreen.LiveQuoteViewModel = quotes[position]
        }
    }

    private inner class CurrencySelectorViewImpl : CurrencySelectorView {
        override fun setCurrencies(currencyViewModel: List<MainScreen.CurrencyViewModel>) {
            currencySelector.adapter = ArrayAdapter(
                this@MainActivity,
                R.layout.support_simple_spinner_dropdown_item, currencyViewModel
            )
        }

        override fun getSelectedCurrency(): CharSequence {
            return (currencySelector.selectedItem as MainScreen.CurrencyViewModel).abrevation
        }

    }
}
