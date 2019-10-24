package com.bhargavms.currencyconverter

import android.app.Activity
import android.os.Bundle
import android.support.annotation.LayoutRes

interface DIActivity<T : Activity> {

    fun createComponent(applicationComponent: ApplicationComponent): ActivityComponent<T>

    fun onReady(savedInstanceState: Bundle?)

    @LayoutRes
    fun getContentViewLayoutId(): Int
}
