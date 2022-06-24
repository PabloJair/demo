package com.baubap.core.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RawRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.baubap.core.R
import com.baubap.core.ui.LoadingUtils
import com.baubap.core.utils.DialogsUtils

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
   protected val binding: T by lazy {bindingInflater.invoke(layoutInflater)  }

    open fun setupAppBar(toolbar: Toolbar, title: String){
        toolbar.title =title
        setSupportActionBar(toolbar)
    }
   protected abstract val bindingInflater: (LayoutInflater) -> T
    open fun setupView(){}
    open fun init(){}
    open fun setupViewModel(){}

    private fun setupConfig(){
        setContentView(requireNotNull(binding.root))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupConfig()
        setupView()
        setupViewModel()
        init()
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    open fun onObserverViewModel(fetchData: BaseFetchData)=
        when(fetchData){
            is BaseFetchData.Error -> onFails(fetchData.message)
            is BaseFetchData.Loader -> onLoader(fetchData.isShow)
            is BaseFetchData.Success -> onSuccess(fetchData.data)
        }


    fun showLoader() =LoadingUtils.showDialog(this,false)
    fun hideLoader() =LoadingUtils.hideDialog()

    fun showError(
        title: String,
        subTitle: String,
        textBtn1: String = "Cancelar",
        onClickBtn1: (() -> Unit)? = null,
        @RawRes lottieFile: Int = R.raw.error,
    ){
        DialogsUtils.showErrorDialog(
            this,
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
            this,
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