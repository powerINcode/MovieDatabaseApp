package com.powerincode.core.ui.dialog.toast

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastViewImpl @Inject constructor(val context: Context): ToastView {
    override fun show(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}