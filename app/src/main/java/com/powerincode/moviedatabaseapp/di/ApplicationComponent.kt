package com.powerincode.moviedatabaseapp.di

import android.content.Context
import com.powerincode.core.di.qualifiers.ApplicationContext
import com.powerincode.data.di.DataModule
import com.powerincode.data.di.MoviesModule
import com.powerincode.moviedatabaseapp.main.di.MainActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        DataModule::class,
        MoviesModule::class
    ]
)
@Singleton
interface ApplicationComponent {

    fun mainActivityComponent(): MainActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@ApplicationContext @BindsInstance context: Context): ApplicationComponent
    }
}

