package com.powerincode.moviedatabaseapp

import android.app.Application
import com.powerincode.moviedatabaseapp.di.ApplicationComponent
import com.powerincode.moviedatabaseapp.di.DaggerApplicationComponent
import timber.log.Timber

class MovieApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        component = DaggerApplicationComponent.factory()
            .create(this, "b391ea8520581a230bf851b2a23312b0")
    }
}