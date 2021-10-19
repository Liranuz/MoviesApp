package com.pilltracker.movies.data.network


data class Resource<out T> constructor(val status: Status, val data: T?, val throwable : Throwable?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        HIDE_LOADING

    }

    companion object {

        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data, null)

        fun <T> error(data: T?, throwable: Throwable?): Resource<T> = Resource(Status.ERROR, data, throwable)

        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data, null)

        fun <T> hideLoading(data: T?): Resource<T> = Resource(Status.HIDE_LOADING, data, null)
    }


}