package com.powerincode.moviedatabaseapp.main.di

import com.powerincode.core.di.activity.ActivityComponent
import com.powerincode.core.di.activity.ActivityComponentFactory
import com.powerincode.core.di.scopes.ActivityScope
import com.powerincode.moviedatabaseapp.main.view.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = [
        MainActivityModule::class
    ]
)
interface MainActivityComponent: ActivityComponent<MainActivity> {

    @Subcomponent.Factory
    interface Factory: ActivityComponentFactory<MainActivityComponent>
}