package com.baubap.demo.home.data.remote.service

import com.baubap.demo.home.data.remote.model.ApiResponseInformationPokemon
import com.baubap.demo.home.data.remote.model.ApiResponsePokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET(EndPoints.POKEMON)
    suspend fun getPokemon(@Query("limit") limit:Int, @Query("offset")offset:Int):
            Response<ApiResponsePokemon>

    @GET(EndPoints.POKEMON)
    suspend fun getInfoPokemon(@Path("name") name:String):
            Response<ApiResponseInformationPokemon>
}