package com.powerincode.moviedatabaseapp.di

import android.content.Context
import com.powerincode.data.di.DataModule
import com.powerincode.moviedatabaseapp.di.qualifiers.ApplicationContext
import com.powerincode.moviedatabaseapp.main.di.MainActivityComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        ApplicationModule::class,
        DataModule::class
    ]
)
interface ApplicationComponent {

    fun mainActivityComponent(): MainActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@ApplicationContext @BindsInstance context: Context): ApplicationComponent
    }
}

