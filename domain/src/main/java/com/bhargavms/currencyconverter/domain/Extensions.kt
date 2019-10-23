package com.bhargavms.currencyconverter.domain

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

internal fun Any.onIO(block: suspend CoroutineScope.() -> Unit): Job =
    CoroutineScope(Dispatchers.IO).launch(EmptyCoroutineContext, CoroutineStart.DEFAULT, block)


operator fun <O> ShowUseCase<Unit, O>.invoke(outputBlock: (O) -> Unit): Job {
    return invoke(Unit, outputBlock)
}
