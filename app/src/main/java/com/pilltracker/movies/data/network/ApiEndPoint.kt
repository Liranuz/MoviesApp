package com.pilltracker.movies.data.network

import com.pilltracker.movies.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiEndPoint {

    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<MovieResponse>

    @GET("discover/movie?primary_release_date.gte=2022-06-06&primary_release_date.lte=2022-10-17")
    fun getPlayingMovies(@Query("api_key") apiKey: String): Single<MovieResponse>




}

