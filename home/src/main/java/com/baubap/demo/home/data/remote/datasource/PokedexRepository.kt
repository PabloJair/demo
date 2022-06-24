package com.baubap.demo.home.data.remote.datasource

import com.baubap.core.base.BaseApiResponse
import com.baubap.core.network.NetworkResult
import com.baubap.demo.home.data.remote.model.ApiResponseInformationPokemon
import com.baubap.demo.home.data.remote.model.ApiResponsePokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokedexRepository @Inject constructor (private val pokedexRepository: PokedexDataSource):
    BaseApiResponse() {

    suspend fun getPaginationPokemon(limit:Int,offset:Int): Flow<NetworkResult<ApiResponsePokemon>> =
        flow {

            emit(NetworkResult.Loading(true))
            emit(safeApiCall { pokedexRepository.getPokemon(limit,offset) })
            emit(NetworkResult.Loading(false))
        }.flowOn(Dispatchers.IO)


    suspend fun getInfoPokemon(name:String):Flow<NetworkResult<ApiResponseInformationPokemon>> =
        flow {
            emit(NetworkResult.Loading(true))
            emit(safeApiCall { pokedexRepository.getInfoPokemon(name) })
            delay(3000)
            emit(NetworkResult.Loading(false))
        }.flowOn(Dispatchers.IO)

}
