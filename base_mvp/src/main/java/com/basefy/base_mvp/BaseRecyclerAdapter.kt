package com.basefy.base_mvp

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class BaseRecyclerAdapter<M : RecyclerItem, VH : BaseViewHolder<M>>
    : RecyclerView.Adapter<VH>(), DiffAdapter {


    private var onItemClick: ((M,position:Int,layoutId:Int) -> Unit) = {_,_ ,_->}

    private var onViewClick: ((M, View) -> Unit) = { _, _ -> }

    var items: List<M> by Delegates.observable(emptyList()) { _, old, new ->
        this@BaseRecyclerAdapter.notifyDiff(old, new)
    }

    fun updateList(newList: MutableList<M>){
/*
        val diffCallBack = DiffUtilsss(items, newList)
*/
       // val diffResult = DiffUtil.calculateDiff(diffCallBack)
        //diffResult.dispatchUpdatesTo(this)
        var before:MutableList<M> = ArrayList<M>()
        before = items as MutableList<M>
        before.addAll(newList)
        items = before

    }

   /* class DiffUtilsss<M> (
        private val oldNumbers: List<M>,
        private val newNumbers: List<M>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldNumbers.size

        override fun getNewListSize(): Int = newNumbers.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldNumbers[oldItemPosition] == newNumbers[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldNumbers[oldItemPosition] == newNumbers[newItemPosition]
        }
    }*/
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