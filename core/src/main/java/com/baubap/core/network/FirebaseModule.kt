package com.baubap.core.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    @Named("FirebaseAuth")
    fun provideLoginFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    @Named("FirebaseDatabase")
    fun provideLoginFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()




}