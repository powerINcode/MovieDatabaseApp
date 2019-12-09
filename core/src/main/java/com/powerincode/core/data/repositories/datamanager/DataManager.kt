package com.powerincode.core.data.repositories.datamanager

import com.powerincode.core.domain.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataManager<T : Any>(val key: String, private val delegate: DataDelegate<T>) {

    fun flow(force: Boolean): Flow<Data<T>> {
        return flow {
            val data = delegate.getFromMemory(key)
            emit(Data.LOADING(data))

            if (data == null) {
                val data2 = delegate.getFromStorage(key)

                if (data2 == null) {
                    val result = loadFromNetworkAndUpdateCache()
                    emit(Data.COMPLETED(result))
                } else {
                    if (force || delegate.isExpired(key, data2)) {
                        val result = loadFromNetworkAndUpdateCache()
                        emit(Data.COMPLETED(result))
                    } else {
                        emit(Data.COMPLETED(data2.value))
                    }
                }
            }
        }
    }

    private suspend fun loadFromNetworkAndUpdateCache() : T {
        val result = delegate.getFromNetwork()
        delegate.setToMemory(key, result)
        delegate.setToStorage(key, result)

        return result
    }
}