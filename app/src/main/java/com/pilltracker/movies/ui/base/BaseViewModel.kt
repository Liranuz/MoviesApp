package com.pilltracker.movies.ui.base

import androidx.lifecycle.ViewModel
import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel() {


    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()


    @CallSuper
    fun onDestroy() {
        clearSubscriptions()
    }

    private fun clearSubscriptions() {
        compositeDisposable.clear()
    }


    override fun onCleared() {
        super.onCleared()
        clearSubscriptions()
    }


}