package com.baubap.demo

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaubapDemoApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

    }
}