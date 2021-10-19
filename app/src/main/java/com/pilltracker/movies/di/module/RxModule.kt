package com.pilltracker.movies.di.module

import com.pilltracker.movies.utils.RxSingleSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RxModule {

    @Singleton
    @Provides
    fun providesScheduler(): RxSingleSchedulers {
        return RxSingleSchedulers.DEFAULT
    }
}