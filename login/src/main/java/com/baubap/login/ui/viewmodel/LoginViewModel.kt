package com.baubap.login.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.baubap.core.base.BaseObservable
import com.baubap.login.data.remote.datasource.RepositoryLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: RepositoryLogin,
    application: Application
): BaseObservable(application){


    fun login(user:String,password:String)=viewModelScope.launch {
        repository.login(user, password)  .collect {
            setupLiveData(it)

        }
    }

    fun register(user:String,password:String)=viewModelScope.launch {
        repository.register(user, password)  .collect {
            setupLiveData(it)

        }
    }
}