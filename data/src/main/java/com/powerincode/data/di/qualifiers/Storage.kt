package com.powerincode.data.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class StorageType {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Memory

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Local
}