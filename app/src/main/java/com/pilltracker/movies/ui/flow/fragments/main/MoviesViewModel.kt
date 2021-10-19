package com.pilltracker.movies.ui.flow.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.network.NetworkController
import com.pilltracker.movies.ui.base.BaseViewModel
import com.pilltracker.movies.utils.RxSingleSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class MoviesViewModel @Inject constructor(
    private val networkController: NetworkController,
    private val rxSingleSchedulers: RxSingleSchedulers
): BaseViewModel() {

    private val _movies = MutableLiveData<MutableList<Result>>()
    val getMovies : LiveData<MutableList<Result>> get() = _movies



    fun getPopularMovies() {

        networkController.getMoviesList()
            .compose (rxSingleSchedulers.applySchedulers() )
            .subscribe { response, throwable ->
                if (throwable != null || response?.results == null) {
                    return@subscribe
                } else {
                    _movies.value = response.results!!
                }
            }
            .addTo(compositeDisposable)


    }

    fun getCurrentDisplaying() {

        networkController.getCurrentDisplaying()
            .compose (rxSingleSchedulers.applySchedulers() )
            .subscribe { response, throwable ->
                if (throwable != null || response?.results == null) {
                    return@subscribe
                } else {
                    _movies.value = response.results!!
                }
            }
            .addTo(compositeDisposable)
    }

}
