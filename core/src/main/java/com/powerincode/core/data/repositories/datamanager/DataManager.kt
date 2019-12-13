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

            try {
                val data = if (memoryData == null) {
                    val storageData = delegate.getFromStorage(key)

                    if (storageData == null) {
                        loadFromNetworkAndUpdateCache()

                    } else {
                        provideDataItem(force, storageData)
                    }
                } else {
                    provideDataItem(force, memoryData)
                }

                emit(data)
            } catch (e: Exception) {
                emit(Data.ERROR(e))
            }
        }
    }

    private fun isNeedToLoadDataFromNetwork(force: Boolean?, block: PrefModelHolder<T>): Boolean {
        return force == true || (force == null && delegate.isExpired(key, block))
    }

    private suspend fun loadFromNetworkAndUpdateCache(): Data<T> {
        val result = delegate.getFromNetwork()
        val valueHolder = PrefModelHolder(result, System.currentTimeMillis())
        delegate.setToMemory(key, valueHolder)
        delegate.setToStorage(key, valueHolder)

        return Data.COMPLETED(result)
    }

    private suspend fun provideDataItem(force: Boolean?, data: PrefModelHolder<T>): Data<T> {
        return if (isNeedToLoadDataFromNetwork(force, data)) {
            val result = loadFromNetworkAndUpdateCache()
            result
        } else {
            Data.COMPLETED(data.value)
        }
    }
}