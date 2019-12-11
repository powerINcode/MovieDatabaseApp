package com.powerincode.core.android

import android.content.SharedPreferences

fun SharedPreferences.put(key: String, value: String) {
    edit().apply {
        putString(key, value)
    }.apply()
}

fun SharedPreferences.put(key: String, value: Int) {
    edit().apply {
        putInt(key, value)
    }.apply()
}

fun SharedPreferences.put(key: String, value: Long) {
    edit().apply {
        putLong(key, value)
    }.apply()
}

fun SharedPreferences.put(key: String, value: Boolean) {
    edit().apply {
        putBoolean(key, value)
    }.apply()
}

fun SharedPreferences.put(key: String, value: Float) {
    edit().apply {
        putFloat(key, value)
    }.apply()
}

fun SharedPreferences.remove(key: String) {
    edit().apply {
        remove(key)
    }.apply()
}

