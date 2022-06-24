package com.baubap.core.network

import com.baubap.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

  private fun loggingInterceptor(): HttpLoggingInterceptor =
      HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

  private fun provideHttpClient(): OkHttpClient =
      OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor())
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()

  @Singleton
  @Provides
  fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

  @Singleton
  @Provides
  @Named("retrofit")
  fun provideRetrofitDemo(): Retrofit =
      Retrofit.Builder()
        .baseUrl(BuildConfig.API_POKEMON)
        .client(provideHttpClient())
        .addConverterFactory(provideConverterFactory())
        .build()
  }

