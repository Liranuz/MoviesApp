package com.pilltracker.movies.di.module.fragments

import com.pilltracker.movies.di.scope.FragmentScope
import com.pilltracker.movies.ui.flow.fragments.main.MoviesFragment
import com.pilltracker.movies.ui.flow.fragments.movie_details.MovieDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentMoviesBuilder {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MoviesFragmentModule::class])
    abstract fun contributeMoviesFragment(): MoviesFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [MovieDetailsFragmentModule::class])
    abstract fun contributeMovieDetailsFragment(): MovieDetailsFragment
}