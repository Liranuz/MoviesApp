package com.pilltracker.movies

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MoviesApplication : Application(), HasAndroidInjector, LifecycleObserver {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>


    override fun androidInjector(): AndroidInjector<Any> =
        androidInjector


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackground(){
        app = this
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForeground() {
        app = this
    }


    companion object {
        var app: MoviesApplication? = null
    }
}