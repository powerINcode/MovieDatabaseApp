package com.powerincode.core.android

import android.content.SharedPreferences

fun SharedPreferences.putString(key: String, value: String) {
    edit().apply {
        putString(key, value)
    }.apply()
}

fun SharedPreferences.remove(key: String) {
    edit().apply {
        remove(key)
    }.apply()
}