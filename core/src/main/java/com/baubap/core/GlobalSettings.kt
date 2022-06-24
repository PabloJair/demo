package com.baubap.core

import android.content.Context
import com.baubap.core.model.UserInformation
import com.blankj.utilcode.util.GsonUtils
import com.blankj.utilcode.util.SPUtils
import com.google.gson.Gson

object GlobalSettings {
  const val USER = "USER"
  const val DEMO_MANAGER = "DEMO_MANAGER"
  private var userInformation: UserInformation? = null
  private var _fcmToken = ""
  var isSession: Boolean = false

  fun saveUser(user: UserInformation) {

    isSession = true
    SPUtils.getInstance(DEMO_MANAGER, Context.MODE_PRIVATE).put(USER, Gson().toJson(user))
    userInformation = user
  }

  fun getUser(): UserInformation? =
      if (userInformation == null) {
        userInformation =
            SPUtils.getInstance(DEMO_MANAGER, Context.MODE_PRIVATE).getString(USER).let {
              GsonUtils.fromJson(it, UserInformation::class.java)
            }
        isSession = userInformation != null
        userInformation
      } else {
        isSession = userInformation != null
        userInformation
      }

  fun validateSession(): Boolean {

    val json = SPUtils.getInstance(DEMO_MANAGER, Context.MODE_PRIVATE).getString(USER)

    return if (json.isNullOrEmpty()) false
    else {
      getUser()
      true
    }
  }

  fun closeSession() {
    SPUtils.getInstance(DEMO_MANAGER, Context.MODE_PRIVATE).apply { remove(USER) }
  }
}
