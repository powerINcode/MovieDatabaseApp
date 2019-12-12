package com.powerincode.core.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.powerincode.core.domain.viewmodels.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import javax.inject.Inject

abstract class BaseActivity<T: BaseViewModel>(@LayoutRes layoutId: Int, viewModelClass: Class<T>): AppCompatActivity(layoutId), CoroutineScope by MainScope() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel: T by lazy {
        ViewModelProviders.of(this, viewModelFactory)[viewModelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onAttach()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onDetach()
    }


    abstract fun inject()

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}