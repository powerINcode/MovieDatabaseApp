package com.powerincode.data.datadelegate

import com.powerincode.core.data.local.PrefModel
import com.powerincode.core.data.local.PrefModelHolder
import com.powerincode.core.data.repositories.datamanager.DataDelegate
import com.powerincode.data.local.Storage

abstract class TimeDataDelegate<T>(
    private val memoryStorage: Storage,
    private val localStorage: Storage,
    private val expiredTimeMills: Long
    ): DataDelegate<T> {
    override fun getFromMemory(key: String): PrefModelHolder<T>? = memoryStorage.get(key, getType())

    override fun setToMemory(key: String, value: PrefModelHolder<T>) {
        memoryStorage.put(key, value)
    }

    override fun getFromStorage(key: String): PrefModelHolder<T>? = localStorage.get(key, getType())

    override fun setToStorage(key: String, value: PrefModelHolder<T>) {
        localStorage.put(key, value)
    }

    override fun isExpired(key: String, cache: PrefModel): Boolean {
        val lastUpdatedAt = cache.lastUpdatedAt
        return System.currentTimeMillis() - lastUpdatedAt > expiredTimeMills
    }
}