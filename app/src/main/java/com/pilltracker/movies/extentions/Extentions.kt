package com.pilltracker.movies.extentions

import android.os.SystemClock
import android.view.View
import android.widget.Button
import androidx.lifecycle.MutableLiveData
import com.pilltracker.movies.data.network.Resource

fun View.visible(isVisible: Boolean, nonVisibleMode: Int) {
    this.visibility =  if (isVisible) View.VISIBLE else nonVisibleMode
}

fun <T> MutableLiveData<Resource<T>>?.showLoading() {
    this?.postValue(Resource.loading(null))
}

fun <T> MutableLiveData<Resource<T>>?.hideLoading() {
    this?.value = Resource.hideLoading(null)
}

fun <T> MutableLiveData<Resource<T>>?.success(data: T) {
    this?.value = Resource.success(data)
}

fun <T> MutableLiveData<Resource<T>>?.failure(error: Throwable?) {
    this?.value = Resource.error(null, error)
}


class SafeClickListener(
    private val interval: Int = 500,
    private val onSafeClick: (View) -> Unit
) : View.OnClickListener {

    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < interval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeClick(v)
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}
