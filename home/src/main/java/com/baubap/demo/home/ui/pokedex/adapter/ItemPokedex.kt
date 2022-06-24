package com.baubap.demo.home.ui.pokedex.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.baubap.core.base.BaseViewHolder
import com.baubap.core.utils.GlideUtils
import com.baubap.demo.home.BuildConfig
import com.baubap.demo.home.data.remote.model.Pokemon
import com.baubap.demo.home.databinding.ItemPokemonBinding
import com.baubap.demo.home.extensions.getIdPokemonForURL


class ItemPokedex(viewGroup: ViewGroup) : BaseViewHolder<ItemPokemonBinding, Pokemon>(
    ItemPokemonBinding.inflate(android.view.LayoutInflater.from(viewGroup.context), viewGroup, false)
) {
    @SuppressLint("ResourceAsColor")
    override fun setup(item: Pokemon) {

        val id = item.url.getIdPokemonForURL()
        val url = "${BuildConfig.API_RESOURCES_IMAGES}${id}.png"
        GlideUtils.loadImage(itemView.context, url, binding.imageView)
        binding.textViewID.text = "#$id"
        binding.textViewName.text=item.name



    }
}
