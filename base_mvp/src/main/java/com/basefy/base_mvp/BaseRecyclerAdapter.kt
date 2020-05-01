package com.basefy.base_mvp

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class BaseRecyclerAdapter<M : RecyclerItem, VH : BaseViewHolder<M>>
    : RecyclerView.Adapter<VH>(), DiffAdapter {

    private var onItemClick: ((M,position:Int,layoutId:Int) -> Unit) = {_,_ ,_->}

    private var onViewClick: ((M, View) -> Unit) = { _, _ -> }

    var items: List<M> by Delegates.observable(emptyList()) { _, old, new ->
        this@BaseRecyclerAdapter.notifyDiff(old, new)
    }


    @LayoutRes
    override fun getItemViewType(position: Int) =
        items[position].layoutId


    override fun onBindViewHolder(holder: VH, position: Int) =
        holder.setOnViewClick(onViewClick)
            .bindItem(items[position], onItemClick)


    override fun getItemCount() = items.size

    fun onItemClick(onClick: ((M,clickedItem:Int,layoutId:Int) -> Unit)): BaseRecyclerAdapter<M, *> {
        this.onItemClick = onClick
        return this
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH


}