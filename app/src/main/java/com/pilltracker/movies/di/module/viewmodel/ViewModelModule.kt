package com.pilltracker.movies.di.module.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pilltracker.movies.ui.flow.activity.MainViewModel
import com.pilltracker.movies.ui.flow.fragments.main.MoviesViewModel
import com.pilltracker.movies.ui.flow.fragments.movie_details.MoviesDetailsViewModel
import com.pilltracker.movies.viewmodelfactory.ViewModelFactory
import com.pilltracker.movies.viewmodelfactory.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel::class)
    abstract fun bindMoviesViewModel(viewModel: MoviesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MoviesDetailsViewModel::class)
    abstract fun bindMovieDetailsViewModel(viewModel: MoviesDetailsViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}