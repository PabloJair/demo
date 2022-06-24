package com.baubap.core.utils

import android.content.Context
import androidx.annotation.RawRes
import com.baubap.core.R
import com.baubap.core.ui.DialogError
import com.baubap.core.ui.DialogSuccess

open class DialogsUtils {

  companion object {
    private var dialogSuccess: DialogSuccess? = null
    private var dialogError: DialogError? = null

    fun showSuccessDialog(
        context: Context?,
        isCancelable: Boolean,
        title: String,
        subTitle: String,
        textBtn1: String = "Cancelar",
        textBtn2: String = "OK",
        onClickBtn1: (() -> Unit)? = null,
        onClickBtn2: (() -> Unit)? = null,
        @RawRes lottieFile: Int = R.raw.success,
    ) {
      hideSuccessDialog()
      if (context != null) {
        try {
          dialogSuccess =
              DialogSuccess(
                  context,
                  title,
                  subTitle,
                  textBtn1,
                  textBtn2,
                  onClickBtn1,
                  onClickBtn2,
                  lottieFile)
          dialogSuccess?.let { jarvisLoader ->
            jarvisLoader.setCanceledOnTouchOutside(true)
            jarvisLoader.setCancelable(isCancelable)
            jarvisLoader.show()
          }
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
    }

    fun hideSuccessDialog() {
      if (dialogSuccess != null && dialogSuccess?.isShowing!!) {
        dialogSuccess =
            try {
              dialogSuccess?.dismiss()
              null
            } catch (e: Exception) {
              null
            }
      }
    }

    fun showErrorDialog(
        context: Context?,
        isCancelable: Boolean,
        title: String,
        subTitle: String,
        textBtn1: String = "Cancelar",
        onClickBtn1: (() -> Unit)? = null,
        @RawRes lottieFile: Int = R.raw.success,
    ) {
      hideErrorDialog()
      if (context != null) {
        try {
          dialogError = DialogError(context, title, subTitle, textBtn1, onClickBtn1, lottieFile)
          dialogError?.let { jarvisLoader ->
            jarvisLoader.setCanceledOnTouchOutside(true)
            jarvisLoader.setCancelable(isCancelable)
            jarvisLoader.show()
          }
        } catch (e: Exception) {
          e.printStackTrace()
        }
      }
    }

    fun hideErrorDialog() {
      if (dialogError != null && dialogError?.isShowing!!) {
        dialogError =
            try {
              dialogError?.dismiss()
              null
            } catch (e: Exception) {
              null
            }
      }
    }
  }
}
