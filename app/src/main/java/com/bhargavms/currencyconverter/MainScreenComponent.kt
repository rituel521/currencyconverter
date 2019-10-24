package com.bhargavms.currencyconverter

import android.app.Activity
import com.bhargavms.currencyconverter.scopes.ScreenScope
import dagger.Subcomponent

@Subcomponent(modules = [MainScreenModule::class])
@ScreenScope
interface MainScreenComponent: ActivityComponent<MainActivity> {
    override fun inject(activity: MainActivity)
}

interface ActivityComponent<T: Activity> {
    fun inject(activity: T)
}
