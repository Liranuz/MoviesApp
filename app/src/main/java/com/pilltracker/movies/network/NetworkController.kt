package com.pilltracker.movies.network

import com.pilltracker.movies.model.MovieResponse
import io.reactivex.Single

interface NetworkController {

    fun getMoviesList(): Single<MovieResponse?>


    fun getCurrentDisplaying(): Single<MovieResponse?>



}