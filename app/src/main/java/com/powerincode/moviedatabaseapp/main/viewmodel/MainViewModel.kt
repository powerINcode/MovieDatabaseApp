package com.powerincode.moviedatabaseapp.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.powerincode.core.domain.viewmodels.BaseViewModel
import com.powerincode.core.ui.dialog.progress.ProgressLoadingView
import com.powerincode.core.ui.dialog.toast.ToastView
import com.powerincode.domain.usecases.movies.GetPopularMovies
import com.powerincode.middleware.movies.Movie
import com.powerincode.moviedatabaseapp.main.MainScreenContract
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val progressDialog: ProgressLoadingView,
    private val toastView: ToastView,
    private val getPopularMovies: GetPopularMovies
) : BaseViewModel(), MainScreenContract.ViewModel {

    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    override val movies: LiveData<List<Movie>> = _movies

    init {
        launch {
            getPopularMovies(null)
                .withProgress(progressDialog)
                .errorHandler(toastView)
                .extractDataSkipError()
                .safeCollect { _movies.value = it }
        }
    }
}