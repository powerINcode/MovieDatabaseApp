package com.powerincode.moviedatabaseapp.main.viewmodel

import com.powerincode.core.domain.viewmodels.BaseViewModel
import com.powerincode.core.ui.dialog.progress.ProgressLoadingView
import com.powerincode.core.ui.dialog.toast.ToastView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainViewModel @Inject constructor(
    private val progressDialog: ProgressLoadingView,
    private val toastView: ToastView
) : BaseViewModel()