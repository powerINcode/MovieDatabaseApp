package com.powerincode.moviedatabaseapp

import android.app.Application
import com.powerincode.moviedatabaseapp.di.ApplicationComponent
import com.powerincode.moviedatabaseapp.di.DaggerApplicationComponent

class MovieApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.factory()
            .create(this)
    }
}