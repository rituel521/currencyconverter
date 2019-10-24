package com.bhargavms.currencyconverter.domain

import kotlinx.coroutines.*
import kotlin.coroutines.EmptyCoroutineContext

internal suspend inline fun <R> Any.onIO(crossinline block: suspend CoroutineScope.() -> R): R =
    withContext(Dispatchers.IO) { block() }

internal fun Any.launch(block: suspend CoroutineScope.() -> Unit): Job {
    return GlobalScope.launch(Dispatchers.Main) { block() }
}

operator fun <O> ShowUseCase<Unit, O>.invoke(
    outputBlock: (O) -> Unit,
    errorBlock: () -> Unit
): Job {
    return invoke(Unit, outputBlock, errorBlock)
}
