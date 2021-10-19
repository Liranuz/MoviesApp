package com.pilltracker.movies.extentions

import android.os.SystemClock
import android.view.View
import android.widget.Button

fun View.visible(isVisible: Boolean, nonVisibleMode: Int) {
    this.visibility =  if (isVisible) View.VISIBLE else nonVisibleMode
}

fun View.show() {
    this.visibility = View.VISIBLE
}


fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun Button.disableButton() {
    isEnabled = false
    alpha = 0.7f
}

fun Button.enableButton() {
    isEnabled = true
    alpha = 1.0f
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
