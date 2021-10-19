package com.pilltracker.movies.ui.flow.activity

import androidx.lifecycle.LiveData
import com.pilltracker.movies.model.Result
import com.pilltracker.movies.ui.base.BaseViewModel
import com.pilltracker.movies.utils.SingleLiveEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(): BaseViewModel() {

    val favorites : MutableList<Result> = arrayListOf()

    private val _isExist = SingleLiveEvent<Boolean>()
    val getIsExit : LiveData<Boolean> = _isExist


    fun onFavoriteIconClicked(movie: Result) {
        val isExistInList: Result? = favorites.find { it.id == movie.id }
        isExistInList?.let {}?: kotlin.run {
            favorites.add(movie)
        }
        _isExist.value = isExistInList != null
    }


    fun onRemoveIconClicked(movie: Result) {
        favorites.remove(movie)

    }

    fun isFavorite(id: Int?): Boolean =  favorites.find { it.id == id } != null
}