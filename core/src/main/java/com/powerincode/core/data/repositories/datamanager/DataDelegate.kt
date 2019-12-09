package com.powerincode.core.data.repositories.datamanager

import com.powerincode.core.data.local.PrefModel
import com.powerincode.core.data.local.PrefModelHolder

interface DataDelegate<T> {
    fun getFromMemory(key: String): T?
    fun setToMemory(key: String, value: T)
    fun getFromStorage(key: String): PrefModelHolder<T>?
    fun setToStorage(key: String, value: T)
    suspend fun getFromNetwork(): T
    fun isExpired(key: String, cache: PrefModel): Boolean
}