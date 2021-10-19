package com.pilltracker.movies.data.network

import android.content.Context
import com.pilltracker.movies.data.network.NetworkConstants.API_KEY
import com.pilltracker.movies.model.MovieResponse
import io.reactivex.Single
import javax.inject.Inject

class NetworkControllerImpl @Inject constructor(
    private val context: Context,
    private val apiEndPoint: ApiEndPoint,
) : NetworkController {


    override fun getMoviesList(): Single<MovieResponse> {
        return apiEndPoint.getPopularMovies(API_KEY)
    }

    override fun getCurrentDisplaying(): Single<MovieResponse> {
        return apiEndPoint.getPlayingMovies(API_KEY)
    }

}