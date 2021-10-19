package com.pilltracker.movies.di.module.fragments

import androidx.recyclerview.widget.LinearLayoutManager
import com.pilltracker.movies.di.scope.FragmentScope
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.flow.fragments.main.MoviesFragment
import com.pilltracker.movies.ui.flow.fragments.main.adapter.MoviesAdapter
import dagger.Module
import dagger.Provides

@Module
class MoviesFragmentModule {

    @Provides
    @FragmentScope
    fun provideRecyclerViewAdapter() : MoviesAdapter {
        val movies: MutableList<Result> = arrayListOf()
        return MoviesAdapter(movies)
    }


    @Provides
    @FragmentScope
    fun provideLinearLayoutManager(fragment: MoviesFragment) =
        LinearLayoutManager(fragment.requireActivity())
}