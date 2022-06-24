package com.baubap.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<V : ViewBinding, M>(var binding: V) :
    RecyclerView.ViewHolder(binding.root) {
  var onClickItem: ((item: M, view: V) -> Unit)? = null
  abstract fun setup(item: M)
}
