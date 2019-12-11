package com.powerincode.data.local

import java.lang.reflect.Type

interface Storage {
    fun put(key: String, value: Any)
    fun <T> get(key: String, type: Type) : T?
    fun remove(key: String)
}