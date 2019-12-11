package com.powerincode.core.ui.dialog.progress

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.powerincode.core.R
import com.powerincode.core.ui.activity.BaseActivity
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

class ProgressDialogImpl @Inject constructor(context: Context): ProgressLoadingView {
    private val counter: AtomicInteger = AtomicInteger()
    private val activity = context as BaseActivity
    private var dialog: Dialog? = null

    override fun show() {
        showDialog(delay = 1000, isCancelable = false)
    }

    override fun dismiss() {
        val loadingCounter = counter.decrementAndGet()
        if (loadingCounter <= 0) {
            counter.set(0)

            dialog?.dismiss()
            dialog = null
        }
    }

    @SuppressLint("InflateParams")
    private fun showDialog(delay: Long, isCancelable: Boolean) {
        counter.incrementAndGet()

        dialog = object: Dialog(activity, R.style.DialogProgressStyle) {
            override fun onBackPressed() {
                if (isCancelable) {
                    activity.onBackPressed()
                } else {
                    super.onBackPressed()
                }
            }
        }

        val contentView = LayoutInflater.from(activity).inflate(R.layout.dialog_progress, null)

        dialog?.setContentView(contentView)
        dialog?.setOnShowListener {
            val loaderView: View = contentView.findViewById(R.id.dialog_progress_root)
            loaderView.animate()
                .alpha(1f)
                .setStartDelay(delay)
                .start()
        }

        dialog?.show()
        dialog?.let {
            it.show()
            it.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        }
    }
}