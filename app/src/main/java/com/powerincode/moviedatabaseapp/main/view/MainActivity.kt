package com.powerincode.moviedatabaseapp.main.view

import android.os.Bundle
import com.powerincode.core.ui.activity.BaseActivity
import com.powerincode.moviedatabaseapp.R
import com.powerincode.moviedatabaseapp.extensions.getApplicationComponent
import com.powerincode.moviedatabaseapp.main.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@UseExperimental(ExperimentalCoroutinesApi::class)
class MainActivity : BaseActivity<MainViewModel>(R.layout.activity_main, MainViewModel::class.java) {

    override fun inject() {
        getApplicationComponent().mainActivityComponent()
            .create(this)
            .inject(this)
    }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}
