package com.pilltracker.movies.data

import com.pilltracker.movies.data.database.room.LocalDatabase
import com.pilltracker.movies.data.network.NetworkController
import com.pilltracker.movies.model.MovieResponse
import com.pilltracker.movies.model.Result
import io.reactivex.Single
import javax.inject.Inject

class RepositoryControllerImpl @Inject internal constructor(
    val networkController: NetworkController,
    val localDatabase: LocalDatabase
) : RepositoryController {


    override fun removeMovie(movieId: Int) {
        localDatabase.removeMovie(movieId)
    }

    override fun saveMovies(movie: Result) {
        localDatabase.saveMovie(movie)
    }

    override fun getFavoriteMovies(): Single<List<Result>> =
        localDatabase.getAllSavedMovies()

    override fun getMoviesList(): Single<MovieResponse> =
        networkController.getMoviesList()


    override fun getCurrentDisplaying(): Single<MovieResponse> =
        networkController.getCurrentDisplaying()


}