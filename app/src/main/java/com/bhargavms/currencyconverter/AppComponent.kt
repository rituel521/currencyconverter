package com.bhargavms.currencyconverter

import com.bhargavms.currencyconverter.data.DataModule
import com.bhargavms.currencyconverter.domain.DomainModule
import com.bhargavms.currencyconverter.scopes.AppScope
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        DomainModule::class
    ]
)
@AppScope
interface AppComponent {
}
