package com.pilltracker.movies.data.database.room

import com.pilltracker.movies.model.Result
import io.reactivex.Single


interface LocalDatabase {

    fun removeMovie(mMovieId: Int)
    fun saveMovie(movie: Result)
    fun getAllSavedMovies() : Single<List<Result>>
}