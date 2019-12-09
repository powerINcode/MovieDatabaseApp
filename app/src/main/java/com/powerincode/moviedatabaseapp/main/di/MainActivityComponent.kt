package com.powerincode.moviedatabaseapp.main.di

import com.powerincode.moviedatabaseapp.main.MainActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [
        MainActivityModule::class
    ]
)
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: MainActivity): MainActivityComponent
    }
}