package com.bhargavms.currencyconverter.domain

import kotlinx.coroutines.Job

interface ShowUseCase<P, O> {
    operator fun invoke(params: P, outputBlock: (O) -> Unit): Job
}

