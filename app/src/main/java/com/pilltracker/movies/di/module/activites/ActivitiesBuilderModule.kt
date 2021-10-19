package com.pilltracker.movies.di.module.activites

import com.pilltracker.movies.di.module.fragments.FragmentMoviesBuilder
import com.pilltracker.movies.di.scope.ActivityScope
import com.pilltracker.movies.ui.flow.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentMoviesBuilder::class])
    abstract fun bindMainActivity(): MainActivity
}