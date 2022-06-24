package com.baubap.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.baubap.core.network.NetworkResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet <T: ViewBinding>: BottomSheetDialogFragment() {
    open var name = ""
    protected val binding: T by lazy { bindingInflater.invoke(layoutInflater) }
    protected abstract val bindingInflater: (LayoutInflater) -> T

    open fun setupView(){}
    open fun init(){}
    open fun setupViewModel(){}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?=binding.root

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupView()
        setupViewModel()
        init()
    }

    open fun  onObserverViewModel(fetchData: NetworkResult<*>){
        when(fetchData){
            is NetworkResult.Error -> onError(fetchData.message, fetchData.data)
            is NetworkResult.Loading -> onLoader(fetchData.isLoading)
            is NetworkResult.Success -> onSuccess(fetchData.data)
        }

    }
    open fun<U> onSuccess(response: U?){}
    open fun<U> onError(message: String?, data: U? = null){}
    open fun onLoader(isLoading: Boolean){}

 }