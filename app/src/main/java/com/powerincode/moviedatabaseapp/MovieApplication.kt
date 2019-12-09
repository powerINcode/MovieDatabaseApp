package com.powerincode.moviedatabaseapp

import android.app.Application
import com.powerincode.moviedatabaseapp.di.DaggerApplicationComponent

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.factory()
            .create(this)
    }
}