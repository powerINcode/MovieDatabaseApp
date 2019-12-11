package com.powerincode.moviedatabaseapp.main.viewmodel

import com.powerincode.core.domain.viewmodels.BaseViewModel
import com.powerincode.core.ui.dialog.progress.ProgressLoadingView
import com.powerincode.core.ui.dialog.toast.ToastView
import com.powerincode.domain.repositories.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val progressDialog: ProgressLoadingView,
    private val toastView: ToastView,
    private val repository: MovieRepository
) : BaseViewModel() {
    init {
//        launch {
//            repository.getPopularMovies(true)
//                .withProgress(progressDialog)
//                .errorHandler(toastView)
//                .safeCollect {
//                }
//        }
    }
}