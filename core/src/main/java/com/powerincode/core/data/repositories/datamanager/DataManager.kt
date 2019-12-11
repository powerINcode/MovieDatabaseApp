package com.powerincode.core.data.repositories.datamanager

import com.powerincode.core.data.local.PrefModelHolder
import com.powerincode.core.domain.repositories.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataManager<T : Any>(val key: String, private val delegate: DataDelegate<T>) {

    fun flow(force: Boolean?): Flow<Data<T>> {
        return flow {

            val memoryData = delegate.getFromMemory(key)
            emit(Data.LOADING(memoryData?.value))

            if (memoryData == null) {
                val storageData = delegate.getFromStorage(key)

                if (storageData == null) {
                    val result = loadFromNetworkAndUpdateCache()
                    emit(Data.COMPLETED(result))
                } else {
                    if (isNeedToLoadDataFromNetwork(force, storageData)) {
                        val result = loadFromNetworkAndUpdateCache()
                        emit(Data.COMPLETED(result))
                    } else {
                        emit(Data.COMPLETED(storageData.value))
                    }
                }
            } else {
                if (isNeedToLoadDataFromNetwork(force, memoryData)) {
                    val result = loadFromNetworkAndUpdateCache()
                    emit(Data.COMPLETED(result))
                } else {
                    emit(Data.COMPLETED(memoryData.value))
                }
            }
        }
    }

    private fun isNeedToLoadDataFromNetwork(force: Boolean?, block: PrefModelHolder<T>): Boolean {
        return force == true || (force == null && delegate.isExpired(key, block))
    }

    private suspend fun loadFromNetworkAndUpdateCache(): T {
        val result = delegate.getFromNetwork()
        val valueHolder = PrefModelHolder(result, System.currentTimeMillis())
        delegate.setToMemory(key, valueHolder)
        delegate.setToStorage(key, valueHolder)

        return result
    }
}