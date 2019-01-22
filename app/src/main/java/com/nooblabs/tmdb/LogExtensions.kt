package com.nooblabs.tmdb

import android.util.Log


val Any.TAG
    get() = this::class.java.simpleName

fun Any.logd(message: Any) {
    Log.d(TAG, message.toString())
}

fun Any.logv(message: Any) {
    Log.v(TAG, message.toString())
}

fun Any.logi(message: Any) {
    Log.i(TAG, message.toString())
}