package com.baubap.demo.home.ui.pokedex.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.baubap.core.base.BaseAdapter
import com.baubap.core.base.BaseViewHolder
import com.baubap.demo.home.data.remote.model.Pokemon
import com.baubap.demo.home.databinding.ItemPokemonBinding
import java.util.*
import kotlin.collections.ArrayList

class AdapterPokedex :
    BaseAdapter<Pokemon, BaseViewHolder<ItemPokemonBinding, Pokemon>>(), Filterable {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ItemPokemonBinding, Pokemon> {
        return  ItemPokedex(parent)
    }
    override fun onBindViewHolder(
        holder: BaseViewHolder<ItemPokemonBinding, Pokemon>,
        position: Int
    ) {
        holder.setup(items[position])

    }

    private var filterPokemon=items
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString().lowercase(Locale.getDefault())
                val resultList: ArrayList<Pokemon> = if(charSearch.isNotEmpty())
                    items.filter { it.name.lowercase(Locale.getDefault()).contains(charSearch) } as ArrayList<Pokemon>
                else
                    filterPokemon

                return FilterResults().apply {
                    values = resultList
                }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items = results?.values as ArrayList<Pokemon>
                notifyDataSetChanged()
            }

        }
    }

}
