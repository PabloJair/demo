package com.baubap.core.base

sealed class BaseFetchData {
    data class Loader(var isShow:Boolean): BaseFetchData()
    data class Error(var message:String): BaseFetchData()
    data class Success(var data:Any): BaseFetchData()
}