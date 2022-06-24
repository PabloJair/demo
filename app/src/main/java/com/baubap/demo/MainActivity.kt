package com.baubap.demo

import android.os.CountDownTimer
import android.view.LayoutInflater
import com.baubap.core.AppNavigation
import com.baubap.core.GlobalSettings
import com.baubap.core.base.BaseActivity
import com.baubap.demo.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity(
    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate
) : BaseActivity<ActivityMainBinding>() {

  private fun goTo() {
    object : CountDownTimer(5000, 1000) {
          override fun onTick(millisUntilFinished: Long) {}
          override fun onFinish() {
            finish()
            if (GlobalSettings.getUser() == null) {
              AppNavigation.openLogin(this@MainActivity)
            } else AppNavigation.openHome(this@MainActivity)
          }
        }
        .start()
  }

  override fun setupView() {

    FirebaseApp.initializeApp(this)
    goTo()
    supportActionBar?.hide()
  }
}
