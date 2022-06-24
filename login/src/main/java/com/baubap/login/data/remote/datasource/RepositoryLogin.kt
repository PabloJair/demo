package com.baubap.login.data.remote.datasource

import com.baubap.core.base.BaseApiResponse
import com.baubap.core.network.NetworkResult
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.internal.concurrent.Task
import javax.inject.Inject

@ActivityRetainedScoped
class RepositoryLogin @Inject constructor(
    private val remoteDataSource: LoginDataSource
): BaseApiResponse()  {

    suspend fun login(email:String,password:String):Flow<NetworkResult<AuthResult>> =
        flow {
            emit(NetworkResult.Loading(true))
            emit(safeApiCallFirebase { remoteDataSource.login(email,password) })
            emit(NetworkResult.Loading(false))
        }.flowOn(Dispatchers.IO)


    suspend fun register(email:String,password:String):Flow<NetworkResult<AuthResult>> =
        flow {
            emit(NetworkResult.Loading(true))
            emit(safeApiCallFirebase { remoteDataSource.register(email,password) })
            emit(NetworkResult.Loading(false))
        }.flowOn(Dispatchers.IO)
}