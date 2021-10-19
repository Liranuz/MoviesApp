package com.pilltracker.movies.di.module.application

import android.app.Application
import android.content.Context
import com.pilltracker.pilltracker_next.di.qualifiers.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ContextModule {

    @ApplicationContext
    @Provides
    fun provideContext(application: Application): Context =
        application.applicationContext

}