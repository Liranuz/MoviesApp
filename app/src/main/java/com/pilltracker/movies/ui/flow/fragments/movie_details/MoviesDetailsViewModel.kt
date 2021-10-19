package com.pilltracker.movies.ui.flow.fragments.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pilltracker.movies.data.RepositoryController
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MoviesDetailsViewModel @Inject constructor(
    private val repositoryController: RepositoryController
) : BaseViewModel() {


   private val _isFavorite = MutableLiveData<Boolean>()

    val isFavorite : LiveData<Boolean> = _isFavorite

    fun saveFavoriteMovie(movie: Result) {
        repositoryController
            .saveMovies(movie)
    }

    fun removeFavoritMovie(movie: Result) {
        repositoryController.removeMovie(movie.id)
    }

    fun isMovieFavorite(movie: Result) {

        repositoryController.getFavoriteMovies()
            .subscribeOn(Schedulers.io())
            .map { list -> list.filter { it.id == movie.id } }
            .subscribe { response, throwable ->
                if (throwable != null) {
                    return@subscribe
                }
                _isFavorite.postValue(!response.isNullOrEmpty())
            }.addTo(compositeDisposable)

    }
}
