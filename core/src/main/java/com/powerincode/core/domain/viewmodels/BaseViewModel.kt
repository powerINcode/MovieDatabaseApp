package com.powerincode.core.domain.viewmodels

import androidx.lifecycle.ViewModel
import com.powerincode.core.ui.dialog.progress.ProgressLoadingView
import com.powerincode.core.ui.dialog.toast.ToastView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import timber.log.Timber


abstract class BaseViewModel: ViewModel(), CoroutineScope by MainScope() {

    fun onAttach() {

    }

    fun onDetach() {

    }

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    fun <T> Flow<T>.withProgress(view: ProgressLoadingView): Flow<T> {
        return this
            .onStart { view.show()  }
            .onCompletion { view.dismiss() }
    }

    fun <T> Flow<T>.errorHandler(view: ToastView): Flow<T> {
        return this.onCompletion { it?.message?.let { message -> view.show(message) } }
    }

    @ExperimentalCoroutinesApi
    @SuppressWarnings
    protected suspend fun <T> Flow<T>.safeCollect(action: suspend (value: T) -> Unit) {
            this.catch { Timber.e(it) }
            .collect(action = action)
    }
}