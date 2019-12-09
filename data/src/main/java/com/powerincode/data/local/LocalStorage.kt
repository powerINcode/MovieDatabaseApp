package com.powerincode.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.powerincode.core.android.putString
import com.powerincode.core.android.remove
import com.powerincode.core.di.qualifiers.ApplicationContext

class LocalStorage constructor(
    val name: String, @ApplicationContext private val context: Context,
    private val gson: Gson
) : Storage {

    private val pref: SharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)

    override fun put(key: String, value: Any) {
        pref.putString(key, gson.toJson(value))
    }

    override fun <T> get(key: String): T? {
        val json = pref.getString(key, null) ?: null
        return gson.fromJson<T>(json, object: TypeToken<T>(){}.type)
    }

    override fun remove(key: String) {
        pref.remove(key)
    }
}