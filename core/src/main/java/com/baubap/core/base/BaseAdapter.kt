package com.baubap.core.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseAdapter<M, I: BaseViewHolder<*, M>>: RecyclerView.Adapter<I>() {

    var onClickItem:((item: M,view: ViewBinding)->Unit)?=null

    var  items: ArrayList<M> = arrayListOf()

    private lateinit var recyclerView: RecyclerView


    fun setupItems(items:ArrayList<M>){
        this.items = items.ifEmpty { arrayListOf() }
        notifyDataSetChanged()
    }

    fun clearItems(){
        items = arrayListOf()
    }

    fun insertNewElements(addElement:ArrayList<M>){
        items.addAll(addElement)
        notifyItemRangeChanged(items.size+1,items.size)


    }
    fun insertNewElement(addElement:M){
        items.add(addElement)
        notifyItemRangeInserted(items.size+1,items.size)
        recyclerView.smoothScrollToPosition(items.size+1)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView= recyclerView
    }

    override fun getItemCount(): Int = items.size


}