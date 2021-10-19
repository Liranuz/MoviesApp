package com.pilltracker.movies.data

import com.pilltracker.movies.model.MovieResponse
import com.pilltracker.movies.model.Result
import io.reactivex.Single

interface RepositoryController {

    fun removeMovie(movieId: Int)

    fun saveMovies(movie: Result)

    fun getFavoriteMovies(): Single<List<Result>>

    fun getMoviesList(): Single<MovieResponse>

    fun getCurrentDisplaying(): Single<MovieResponse>
}