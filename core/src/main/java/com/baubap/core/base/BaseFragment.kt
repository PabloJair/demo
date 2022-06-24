package com.baubap.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.baubap.core.R
import com.baubap.core.utils.DialogsUtils

abstract class BaseFragment <T: ViewBinding>: Fragment() {
    open var name = ""
    val binding: T by lazy { bindingInflater.invoke(layoutInflater) }

    abstract val bindingInflater: (LayoutInflater) -> T

    open fun setupView(){}
    open fun setupObserver(){}
    open fun setupViewModel(){}
    open fun init(){}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = binding.root.apply {
        setupViewModel()
        setupObserver()
        setupView()
        init()
    }


    open fun onObserverViewModel(fetchData: BaseFetchData){
        when(fetchData){
            is BaseFetchData.Error -> onFails(fetchData.message)
            is BaseFetchData.Loader -> onLoader(fetchData.isShow)
            is BaseFetchData.Success -> onSuccess(fetchData.data)
        }

    }
    fun showError(
        title: String,
        subTitle: String,
        textBtn1: String = "Cancelar",
        onClickBtn1: (() -> Unit)? = null,
        @RawRes lottieFile: Int = R.raw.success,
    ){

        DialogsUtils.showErrorDialog(
            requireContext(),
            false,
            title,
            subTitle,
            textBtn1,
            onClickBtn1,
            lottieFile
        )
    }
    fun showSuccess(
        title: String,
        subTitle: String,
        textBtn1: String = "Cancelar",
        textBtn2: String = "OK",
        onClickBtn1: (() -> Unit)? = null,
        onClickBtn2: (() -> Unit)? = null,
        @RawRes lottieFile: Int = R.raw.success,
    ){

        DialogsUtils.showSuccessDialog(
            requireContext(),
            false,
            title,
            subTitle,
            textBtn1,
            textBtn2,
            onClickBtn1,
            onClickBtn2,
            lottieFile
        )
    }
    open fun onSuccess(data: Any){}
    open fun onFails(message: String){}
    open fun onLoader(data: Boolean){}

}