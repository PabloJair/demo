package com.baubap.demo.home.extensions

import com.baubap.demo.home.BuildConfig
import com.baubap.demo.home.data.remote.service.EndPoints

fun String.getIdPokemonForURL()=
    this.trimEnd('/').replace("${BuildConfig.API_POKEMON}${EndPoints.POKEMON}/","")