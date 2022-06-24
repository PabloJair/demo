package com.baubap.login.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    @Singleton
    @Provides
    fun provideLoginFirebaseAuth(@Named("FirebaseAuth") firebaseAuth: FirebaseAuth): FirebaseAuth = firebaseAuth

    @Singleton
    @Provides
    fun provideLoginFirebaseDatabase(@Named("FirebaseAuthDatabase") firebaseDatabase: FirebaseDatabase): FirebaseDatabase = firebaseDatabase
}