package com.bhargavms.currencyconverter.domain

import kotlinx.coroutines.Job

interface ShowUseCase<P, O> {
    fun invoke(params: P, outputBlock: (O) -> Unit): Job
}

