package com.bhargavms.currencyconverter.domain.quotes

import com.bhargavms.currencyconverter.domain.IO
import com.bhargavms.currencyconverter.domain.MAIN
import com.bhargavms.currencyconverter.domain.currency.Currency
import com.bhargavms.currencyconverter.domain.currency.SupportedCurrenciesRepo
import com.bhargavms.currencyconverter.testutil.shouldEqual
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowLiveQuotesImplTest {

    private lateinit var showLiveQuotesImpl: ShowLiveQuotesImpl

    @Mock
    lateinit var liveQuotesRepo: LiveQuotesRepo
    @Mock
    lateinit var supportedCurrenciesRepo: SupportedCurrenciesRepo

    @Before
    fun setup() {
        IO = Dispatchers.Unconfined
        MAIN = Dispatchers.Unconfined
        showLiveQuotesImpl = ShowLiveQuotesImpl(
            liveQuotesRepo, supportedCurrenciesRepo
        )
    }

    @org.junit.Test
    fun testInvoke_success() = runBlocking {
        // ARRANGE
        whenever(liveQuotesRepo.getLiveQuotesForCurrency("blah"))
            .thenReturn(listOf(Quote("blah", Quote.ValueConverter(12.0))))
        whenever(supportedCurrenciesRepo.getSupportedCurrencies())
            .thenReturn(mapOf("blah" to Currency("blah", "really blah")))
        val outBlockMock = mock<(List<QuoteForCurrency>) -> Unit>()
        // ACT
        showLiveQuotesImpl(ShowQuotesFor("blah", 12.0), outBlockMock, mock())
        // ASSERT
        verify(outBlockMock).invoke(argThat {
            this shouldEqual listOf(QuoteForCurrency(Currency("blah", "really blah"), 144.0))
            true
        })
    }

    @org.junit.Test
    fun testInvoke_whenRepoThrows_callsErrorBlock() = runBlocking {
        // ARRANGE
        whenever(supportedCurrenciesRepo.getSupportedCurrencies()).thenThrow(RuntimeException())
        val outBlockMock = mock<(List<QuoteForCurrency>) -> Unit>()
        val errorBlockMock = mock<() -> Unit>()
        // ACT
        showLiveQuotesImpl(ShowQuotesFor("blah", 12.0), outBlockMock, errorBlockMock)
        // ASSERT
        verifyZeroInteractions(outBlockMock)
        verify(errorBlockMock).invoke()
    }
}
