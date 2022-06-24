package com.baubap.demo.home.ui.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.baubap.core.base.BaseObservable
import com.baubap.demo.home.data.remote.datasource.PokedexRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(
    private val repository: PokedexRepository,
    application: Application
): BaseObservable(application){


    fun getPokedex(limit:Int, offset:Int)=viewModelScope.launch {
        repository.getPaginationPokemon(limit, offset).collect { setupLiveData(it) }
    }

    fun getInfoPokemon(name:String)=viewModelScope.launch {
        repository.getInfoPokemon(name)  .collect { setupLiveData(it) }
    }
}