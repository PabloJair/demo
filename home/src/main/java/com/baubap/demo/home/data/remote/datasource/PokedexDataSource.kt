package com.baubap.demo.home.data.remote.datasource

import com.baubap.demo.home.data.remote.service.PokemonService
import javax.inject.Inject

class PokedexDataSource
@Inject
constructor(
    private val service: PokemonService,
) {

  suspend fun getPokemon(limit: Int, offset: Int) = service.getPokemon(limit, offset)

  suspend fun getInfoPokemon(name: String) = service.getInfoPokemon(name)
}
