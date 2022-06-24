package com.baubap.login.ui

import android.view.LayoutInflater
import androidx.activity.viewModels
import com.baubap.core.AppNavigation
import com.baubap.core.base.BaseActivity
import com.baubap.login.databinding.ActivityLoginBinding
import com.baubap.login.ui.dialogs.DialogRegister
import com.baubap.login.ui.viewmodel.LoginViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity(
    override val bindingInflater:
        (LayoutInflater) -> ActivityLoginBinding =ActivityLoginBinding::inflate
): BaseActivity<ActivityLoginBinding>() {
    private val viewModel by viewModels<LoginViewModel>()

    override fun setupView() {
        FirebaseApp.initializeApp(this)
        supportActionBar?.hide()


        binding.apply {
            login.setOnClickListener {

                binding.user.error =null
                binding.password.error =null

                binding.user.editText?.validEmail {
                    binding.user.error ="Correo invalido"
                }
                binding.password.editText?.nonEmpty {
                    binding.password.error ="Contraseña necesaria"
                }
                if((binding.user.editText?.validEmail("Correo invalido")==true) && binding.password.editText?.nonEmpty("Contraseña necesaria")==true){
                    viewModel.login(binding.user.editText?.text.toString(),
                        binding.password.editText?.text.toString())
                }

            }

                    goToApps.setOnClickListener {
                DialogRegister.newInstance().apply {
                    onReturnEmailPassword = { password, email->
                    viewModel.register(email,password)
                        this.dismiss()
                }

                }.show(supportFragmentManager,"REGISTER")
            }
        }


    }
    override fun setupViewModel() {
        viewModel.fetchData().observe(this,::onObserverViewModel)
    }

    override fun onSuccess(data: Any) {
        if(data is AuthResult){

            showSuccess("Bienvenido","Gracias por usar esta App demo","","Ir a Home",null,{
                AppNavigation.openHome(this)

            })
        }

    }

    override fun onFails(message: String) {
        showError(title = "Error en login","Valida tus usuario o contraseña","Cerrar")
    }
    override fun onLoader(data: Boolean) {
        if(data)
            showLoader()
        else
            hideLoader()

    }
}