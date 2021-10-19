package com.pilltracker.movies.di.component

import android.app.Application
import com.pilltracker.movies.MoviesApplication
import com.pilltracker.movies.di.module.RoomModule.StorageModule
import com.pilltracker.movies.di.module.RxModule
import com.pilltracker.movies.di.module.activites.ActivitiesBuilderModule
import com.pilltracker.movies.di.module.application.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ActivitiesBuilderModule::class,
        RxModule::class,
        StorageModule::class
    ]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application : Application ): Builder
        fun build() : ApplicationComponent
    }

    fun inject(application: MoviesApplication)
}