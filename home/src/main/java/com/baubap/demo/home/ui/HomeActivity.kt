package com.baubap.demo.home.ui

import android.view.LayoutInflater
import com.baubap.core.base.BaseActivity
import com.baubap.demo.home.databinding.HomeLayoutBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity (
    override val bindingInflater:
        (LayoutInflater) -> HomeLayoutBinding =HomeLayoutBinding::inflate) :
    BaseActivity<HomeLayoutBinding>() {

    override fun setupView() {}

}