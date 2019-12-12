package com.powerincode.moviedatabaseapp.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.powerincode.core.ui.activity.BaseActivity
import com.powerincode.moviedatabaseapp.R
import com.powerincode.moviedatabaseapp.extensions.getApplicationComponent
import com.powerincode.moviedatabaseapp.main.viewmodel.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject

class MainActivity : BaseActivity(R.layout.activity_main) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun inject() {
        getApplicationComponent().mainActivityComponent().create(this).inject(this)
    }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }
}
