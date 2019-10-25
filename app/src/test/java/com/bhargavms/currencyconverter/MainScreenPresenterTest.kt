package com.bhargavms.currencyconverter

import com.bhargavms.currencyconverter.domain.currency.Currency
import com.bhargavms.currencyconverter.domain.currency.ShowSupportedCurrencies
import com.bhargavms.currencyconverter.domain.quotes.ShowLiveQuotes
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Job
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainScreenPresenterTest {

    private lateinit var presenter: MainScreenPresenter

    @Mock
    lateinit var showSupportedCurrencies: ShowSupportedCurrencies
    @Mock
    lateinit var showLiveQuotes: ShowLiveQuotes
    @Mock
    lateinit var mainScreen: MainScreen
    @Mock
    lateinit var job: Job

    @Before
    fun setUp() {
        presenter = MainScreenPresenter(
            showSupportedCurrencies, showLiveQuotes
        )
    }

    @Test
    fun onDestoryScreenCancelsAllJobs() {
        whenever(showSupportedCurrencies(any(), any(), any())).thenReturn(job)
        whenever(job.isActive).thenReturn(true)

        presenter.onCreateScreen(mainScreen)
        presenter.onDestroyScreen()

        verify(job).cancel()
    }

    @Test
    fun onCreateScreen() {
        whenever(showSupportedCurrencies(any(), any(), any())).thenReturn(job)

        presenter.onCreateScreen(mainScreen)

        val outBlockCaptor = argumentCaptor<(Map<String, Currency>) -> Unit>()
        verify(showSupportedCurrencies).invoke(eq(Unit), outBlockCaptor.capture(), any())
        outBlockCaptor.firstValue.invoke(mapOf("blah" to Currency("blah", "really blah")))
        verify(mainScreen).apply {
            showLoader()
            hideLoader()
            showCurrencies(listOf(MainScreen.CurrencyViewModel("blah", "really blah")))
        }
    }

    @Test
    fun onCreateScreen_error_callsMainScreenShowError() {
        whenever(showSupportedCurrencies(any(), any(), any())).thenReturn(job)

        presenter.onCreateScreen(mainScreen)

        val errorCaptor = argumentCaptor<() -> Unit>()
        verify(showSupportedCurrencies).invoke(eq(Unit), any(), errorCaptor.capture())
        errorCaptor.firstValue.invoke()
        verify(mainScreen).apply {
            showLoader()
            hideLoader()
            showNetworkError()
        }
    }
}
