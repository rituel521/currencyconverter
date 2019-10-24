package com.bhargavms.currencyconverter

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

@Module
class AppModule(private val application: Application) {
    @Provides
    fun provideAppContext(): Context {
        return application
    }
}
