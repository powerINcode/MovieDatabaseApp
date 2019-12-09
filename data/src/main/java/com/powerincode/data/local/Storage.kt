package com.powerincode.data.local

interface Storage {
    fun put(key: String, value: Any)
    fun <T> get(key: String) : T?
    fun remove(key: String)
}