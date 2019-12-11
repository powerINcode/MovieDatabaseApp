package com.powerincode.core.di.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.powerincode.core.di.scopes.ActivityScope
import com.powerincode.core.ui.dialog.progress.ProgressDialogImpl
import com.powerincode.core.ui.dialog.progress.ProgressLoadingView
import com.powerincode.core.ui.dialog.toast.ToastView
import com.powerincode.core.ui.dialog.toast.ToastViewImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
abstract class BaseActivityModule {

    @Binds
    @ActivityScope
    abstract fun provideProgressDialog(dialog: ProgressDialogImpl): ProgressLoadingView

    @Binds
    @ActivityScope
    abstract fun provideToastView(view: ToastViewImpl): ToastView

    @Module
    companion object {
        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideViewModelFactory(viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    val viewModelProvider =
                        viewModels[modelClass]
                            ?: throw IllegalArgumentException("ViewModel class $modelClass not found")
                    return viewModelProvider.get() as T
                }
            }
    }
}