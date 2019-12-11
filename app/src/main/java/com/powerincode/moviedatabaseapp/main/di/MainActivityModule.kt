package com.powerincode.moviedatabaseapp.main.di

import androidx.lifecycle.ViewModel
import com.powerincode.core.di.activity.BaseActivityModule
import com.powerincode.core.di.qualifiers.ViewModelKey
import com.powerincode.core.di.scopes.ActivityScope
import com.powerincode.moviedatabaseapp.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(
    includes = [
        BaseActivityModule::class
    ]
)
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityScope
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainModule(viewModel: MainViewModel): ViewModel
}