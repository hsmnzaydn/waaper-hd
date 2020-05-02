package com.basefy.base_mvp


import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.basefy.base_mvp.RecyclerItem

interface DiffAdapter {

    fun <A : RecyclerView.Adapter<*>, M : RecyclerItem> A.notifyDiff(
        old: List<M>,
        new: List<M>,
        compareItems: (M, M) -> Boolean = { oldItem, newItem ->
            oldItem.comporeId == newItem.comporeId
        },
        compareContents: (M, M) -> Boolean = { oldItem, newItem ->
            (oldItem == newItem)
        },
        changePayLoad: (M, M) -> Any? = { _, _ -> }
    ) {
        val diff = DiffUtil.calculateDiff(
            object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    compareItems(old[oldItemPosition], new[newItemPosition])

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                    compareContents(old[oldItemPosition], new[newItemPosition])

                override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? =
                    changePayLoad(new[newItemPosition], old[oldItemPosition])

                override fun getOldListSize() = old.size

                override fun getNewListSize() = new.size
            }
        )

        diff.dispatchUpdatesTo(this)
    }

}

/*

interface DiffAdapter {

    fun <T> RecyclerView.Adapter<*>.autoNotify(oldList: List<T>, newList: List<T>, compare: (T, T) -> Boolean) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compare(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun getOldListSize() = oldList.size
            override fun getNewListSize() = newList.size
        })
        diff.dispatchUpdatesTo(this)
    }

}*/
