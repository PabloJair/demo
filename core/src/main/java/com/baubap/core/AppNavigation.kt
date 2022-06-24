package com.baubap.core

import android.content.Context
import android.content.Intent

object AppNavigation {

  fun openLogin(context: Context) = open(context, "com.baubap.demo.login")

  fun openHome(context: Context) = open(context, "com.baubap.demo.home")

  private fun open(context: Context, action: String) =
      context.startActivity(intentClearTop(internalIntent(context, action)))

  private fun intentClearTop(intent: Intent): Intent {

    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
    return intent
  }

  private fun internalIntent(context: Context, action: String) =
      Intent(action).setPackage(context.packageName)
}
