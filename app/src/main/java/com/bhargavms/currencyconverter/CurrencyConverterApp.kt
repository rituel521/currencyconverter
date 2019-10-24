package com.bhargavms.currencyconverter

import android.app.Application
import com.bhargavms.currencyconverter.api.NetworkApiModule
import com.bhargavms.currencyconverter.data.DataModule
import com.bhargavms.currencyconverter.domain.DomainModule

class CurrencyConverterApp: Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(Injector(
            DaggerApplicationComponent.builder()
                .appModule(AppModule(this))
                .dataModule(DataModule())
                .domainModule(DomainModule())
                .networkApiModule(NetworkApiModule())
                .build()
        ))
    }
}
