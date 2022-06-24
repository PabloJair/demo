package com.baubap.core.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.baubap.core.network.NetworkResult

abstract class BaseObservable(application: Application): AndroidViewModel(application) {

     private var loader        = MutableLiveData<Boolean>()
     private var success       = MutableLiveData<Any>()
     private var error         = MutableLiveData<String>()

     open fun fetchData(): MediatorLiveData<BaseFetchData> =
          MediatorLiveData<BaseFetchData>().apply{
          addSource(loader) {
               if (it != null)
                    value = BaseFetchData.Loader(it)
          }

          addSource(success) {
               if (it != null)
                    value = BaseFetchData.Success(it)
          }

          addSource(error) {

               if (it != null)
                    value = BaseFetchData.Error(it)

          }
     }

     fun <T> setupLiveData(networkResult: NetworkResult<T>){
          when(networkResult){
               is NetworkResult.Success->this.success.value = networkResult.data
               is NetworkResult.Error->this.error.value = networkResult.message
               is NetworkResult.Loading->this.loader.value = networkResult.isLoading

          }

     }

}