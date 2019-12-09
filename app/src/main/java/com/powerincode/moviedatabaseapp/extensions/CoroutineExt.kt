package com.powerincode.moviedatabaseapp.extensions

import com.powerincode.core.domain.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

fun <T> Flow<Data<T>>.extractData(): Flow<Data<T>> = transform { value ->
    if (value is Data.COMPLETED) return@transform emit(value)
}