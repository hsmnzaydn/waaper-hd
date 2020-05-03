package com.basefy.base_mvp

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class BaseRecyclerAdapter<M : RecyclerItem, VH : BaseViewHolder<M>>
    : ListAdapter<M, VH>(DiffCallback<M>()) {


    private var onItemClick: ((M, position: Int, layoutId: Int) -> Unit) = { _, _, _ -> }

    private var onViewClick: ((M, View) -> Unit) = { _, _ -> }

    private var onPagenation: (() -> Unit?)? = null


    var items: List<M> by Delegates.observable(emptyList()) { _, old, new ->
        this@BaseRecyclerAdapter.submitList(new)
    }

    fun updateList(recyclerView: RecyclerView) {
        recyclerView.getLayoutManager()!!.onRestoreInstanceState(recyclerView.getLayoutManager()!!.onSaveInstanceState()!!)
    }

    fun changeData(position: Int) {
        notifyItemChanged(position)
    }

    fun reciveBottom(onPagenation: () -> Unit) {
        this.onPagenation = onPagenation
    }

    @LayoutRes
    override fun getItemViewType(position: Int) =
        items[position].layoutId


    override fun onBindViewHolder(holder: VH, position: Int) {

        if(position==items.size-10){
            onPagenation?.let { it() }
        }
        return holder.setOnViewClick(onViewClick)
            .bindItem(items[position], onItemClick)
    }


    override fun getItemCount() = items.size

    fun onItemClick(onClick: ((M, clickedItem: Int, layoutId: Int) -> Unit)): BaseRecyclerAdapter<M, *> {
        this.onItemClick = onClick
        return this
    }

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    open class DiffCallback<M : RecyclerItem> : DiffUtil.ItemCallback<M>() {
        override fun areItemsTheSame(oldItem: M, newItem: M): Boolean {

            return oldItem.hashCode() == newItem.hashCode()
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: M, newItem: M) = oldItem == newItem
    }


}