package com.powerincode.core.domain.usecase

interface UseCase<in T, out V> {
    operator fun invoke(params: T): V
}