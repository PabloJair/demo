package com.baubap.demo.home.data.remote.model
import com.google.gson.annotations.SerializedName


data class ApiResponsePokemon(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val pokemons: List<Pokemon>
)

data class Pokemon(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)