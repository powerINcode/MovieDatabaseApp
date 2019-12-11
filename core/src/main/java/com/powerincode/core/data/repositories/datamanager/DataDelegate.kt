package com.powerincode.core.data.repositories.datamanager

import com.powerincode.core.data.local.PrefModel
import com.powerincode.core.data.local.PrefModelHolder
import java.lang.reflect.Type

interface DataDelegate<T> {
    fun getFromMemory(key: String): PrefModelHolder<T>?
    fun setToMemory(key: String, value: PrefModelHolder<T>)
    fun getFromStorage(key: String): PrefModelHolder<T>?
    fun setToStorage(key: String, value: PrefModelHolder<T>)
    suspend fun getFromNetwork(): T
    fun isExpired(key: String, cache: PrefModel): Boolean
    fun getType(): Type
}