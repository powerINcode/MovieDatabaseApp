package com.powerincode.core.di.activity

import androidx.appcompat.app.AppCompatActivity

interface ActivityComponent<T : AppCompatActivity> {
    fun inject(target: T)
}