package com.pilltracker.movies.ui.flow.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pilltracker.movies.data.RepositoryController
import com.pilltracker.movies.data.network.Resource
import com.pilltracker.movies.extentions.failure
import com.pilltracker.movies.extentions.hideLoading
import com.pilltracker.movies.extentions.showLoading
import com.pilltracker.movies.extentions.success
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseViewModel
import com.pilltracker.movies.utils.RxSingleSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import java.net.SocketTimeoutException
import javax.inject.Inject


class MoviesViewModel @Inject constructor(
    private val repositoryController: RepositoryController,
    private val rxSingleSchedulers: RxSingleSchedulers
): BaseViewModel() {

    private val _movies = MutableLiveData<Resource<MutableList<Result>>>()
    val getMovies : LiveData<Resource<MutableList<Result>>> get() = _movies

    fun getPopularMovies() {
        repositoryController.getMoviesList()
            .compose (rxSingleSchedulers.applySchedulers() )
            .doOnSubscribe { _movies.showLoading() }
            .doFinally { _movies.hideLoading() }
            .subscribeBy(onError = { onError1(it)}, onSuccess = { onSuccess1(it.results!!)})
            .addTo(compositeDisposable)
    }

    fun getCurrentDisplaying() {
        repositoryController.getCurrentDisplaying()
            .compose (rxSingleSchedulers.applySchedulers() )
            .doOnSubscribe { _movies.showLoading() }
            .doFinally { _movies.hideLoading() }
            .subscribeBy(onError = { onError1(it)}, onSuccess = { onSuccess1(it.results!!)})
            .addTo(compositeDisposable)
    }

    fun getFavoritesMovies() {
        repositoryController.getFavoriteMovies()
            .compose (rxSingleSchedulers.applySchedulers())
            .doOnSubscribe { _movies.showLoading() }
            .doFinally { _movies.hideLoading() }
            .subscribeBy(onError = { onError1(it)}, onSuccess = { onSuccess1(it.toMutableList())})
            .addTo(compositeDisposable)
    }

    private fun onSuccess1(movieList: MutableList<Result>) {
        _movies.success(movieList)
    }

    private fun onError1(t: Throwable?) {

        t?.let { _movies.failure(it) }



    }

}
