package com.pilltracker.movies.di.module.application

import com.pilltracker.movies.di.module.network.NetworkModule
import com.pilltracker.movies.di.module.viewmodel.ViewModelModule
import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        NetworkModule::class,
        ContextModule::class
    ]
)
class ApplicationModule {
}