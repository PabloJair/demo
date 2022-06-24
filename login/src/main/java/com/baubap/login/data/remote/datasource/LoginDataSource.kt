package com.baubap.login.data.remote.datasource

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginDataSource @Inject constructor(private val authFirebaseAuth: FirebaseAuth) {

    suspend fun login(email:String, password:String) = authFirebaseAuth.signInWithEmailAndPassword(email,password).await()

    suspend fun register(email:String,password:String) = authFirebaseAuth.createUserWithEmailAndPassword(email,password).await()

}