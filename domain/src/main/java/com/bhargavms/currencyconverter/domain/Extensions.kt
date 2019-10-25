package com.bhargavms.currencyconverter.domain

import kotlinx.coroutines.*

var IO: CoroutineDispatcher = Dispatchers.IO
var MAIN: CoroutineDispatcher = Dispatchers.Main

internal suspend inline fun <R> Any.onIO(crossinline block: suspend CoroutineScope.() -> R): R =
    withContext(IO) { block() }

internal fun Any.launch(block: suspend CoroutineScope.() -> Unit): Job {
    return GlobalScope.launch(MAIN) { block() }
}

operator fun <O> ShowUseCase<Unit, O>.invoke(
    outputBlock: (O) -> Unit,
    errorBlock: () -> Unit
): Job {
    return invoke(Unit, outputBlock, errorBlock)
}
