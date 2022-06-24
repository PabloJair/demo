package com.baubap.login.ui.dialogs

import android.view.LayoutInflater
import com.baubap.core.base.BaseBottomSheet
import com.baubap.login.databinding.DialogRegisterBinding

class DialogRegister(
    override val bindingInflater: (LayoutInflater) -> DialogRegisterBinding =
        DialogRegisterBinding::inflate
) : BaseBottomSheet<DialogRegisterBinding>() {

  var onReturnEmailPassword: ((password: String, email: String) -> Unit)? = null

  override fun setupView() {

    binding.login.setOnClickListener {
      if (!binding.password.editText?.text.isNullOrEmpty() ||
          !binding.email.editText?.text.isNullOrEmpty()) {
        onReturnEmailPassword?.invoke(
            binding.password.editText?.text.toString(), binding.email.editText?.text.toString())
      }
    }
  }

  companion object {
    fun newInstance(): DialogRegister =DialogRegister()
  }
}
