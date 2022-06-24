package com.baubap.core.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.RawRes
import com.baubap.core.R
import com.baubap.core.databinding.FragmentDialogBinding

class DialogSuccess(context: Context,
                    val title:String,
                    private val subTitle:String,
                    private val textBtn1:String="Cancelar",
                    private val textBtn2:String="OK",
                    private val onClickBtn1:(()->Unit)?=null,
                    private val onClickBtn2:(()->Unit)?=null,
                    @RawRes var lottieFile:Int= R.raw.success,
                    ):Dialog(context) {

    val binding: FragmentDialogBinding = FragmentDialogBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.apply {

            btn1.text = textBtn1
            btn1.visibility = if (textBtn1.isEmpty()) View.GONE else View.VISIBLE
            btn2.text = textBtn2
            btn2.visibility = if (textBtn2.isEmpty()) View.GONE else View.VISIBLE

            titleDialog.text = title
            messageDialog.text = subTitle

            btn1.setOnClickListener {
                onClickBtn1?.invoke()
                dismiss()

            }
            btn2.setOnClickListener {
                onClickBtn2?.invoke()
                dismiss()

            }
            iconLottie.setAnimation(lottieFile)
        }

        window?.apply {
            setLayout(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)

            setBackgroundDrawableResource(R.color.white_transparent)
        }
    }
}