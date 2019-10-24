package com.bhargavms.currencyconverter

import android.app.Activity
import android.app.Application
import android.os.Bundle

class Injector(private val applicationComponent: ApplicationComponent) : Application.ActivityLifecycleCallbacks {

    private val componentsCache: MutableMap<Class<out Activity>, Any> = mutableMapOf()

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {

    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {
        if (activity?.isFinishing == true) {
            componentsCache.remove(activity::class.java)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        if (activity == null) return
        when (activity) {
            is MainActivity -> {
                performCreate(activity, savedInstanceState) {
                    it.mainScreenComponent(MainScreenModule(activity))
                }
            }
        }
    }

    private inline fun <reified T> performCreate(
        activity: T, savedInstanceState: Bundle?, creator: (ApplicationComponent) -> ActivityComponent<T>
    ) where T : Activity, T : DIActivity<T> {
        activity.setContentView(activity.getContentViewLayoutId())
        componentsCache[T::class.java] ?: creator(applicationComponent).also {
            componentsCache[T::class.java] = it
        }.inject(activity)
        activity.onReady(savedInstanceState)
    }
}
