package com.baubap.demo.home.data.repository

import com.baubap.demo.home.data.remote.service.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class PokedexModule {

    @Singleton
    @Provides
    fun provideCurrencyService(@Named("retrofit") retrofit: Retrofit): PokemonService =
        retrofit.create(PokemonService::class.java)
}