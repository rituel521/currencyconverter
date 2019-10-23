package com.bhargavms.currencyconverter

interface MainScreenDelegate {
}

interface CurrencySelectorView {

}

interface CurrencyEditorView {

}

class MainScreenDelegateImpl(
    private val currencySelectorView: CurrencySelectorView,
    private val currencyPriceEditorView: CurrencyEditorView,
    private val mainScreenPresenter: MainScreenPresenter
) {

}
