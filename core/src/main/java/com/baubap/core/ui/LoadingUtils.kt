package com.baubap.core.ui

import android.content.Context

open class LoadingUtils {

  companion object {
    private var dialogLoader: DialogLoader? = null
    fun showDialog(context: Context?, isCancelable: Boolean) {
      hideDialog()
      if (context != null) {
        try {
          dialogLoader =
              DialogLoader(context).apply {
                setCanceledOnTouchOutside(true)
                setCancelable(isCancelable)
                show()
              }
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
    }

    fun hideDialog() {
      if (dialogLoader != null && dialogLoader?.isShowing!!) {
        dialogLoader =
            try {
              dialogLoader?.dismiss()
              null
            } catch (e: Exception) {
              null
            }
      }
    }
  }
}
