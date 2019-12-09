package com.powerincode.core.domain

sealed class Data<T: Any?> {
    class LOADING<T>(val data: T?): Data<T>()
    class ERROR<T>(val cause: Throwable): Data<T>()
    class COMPLETED<T>(val data: T): Data<T>()
}