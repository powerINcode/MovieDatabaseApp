package com.powerincode.core.di.activity

import android.content.Context
import dagger.BindsInstance

interface ActivityComponentFactory<T> {
    fun create(@BindsInstance context: Context): T
}