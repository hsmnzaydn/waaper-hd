package com.basefy.core_utility

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessOnScrollListener() :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop()
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToEnd()
        }

        if (dy < 0) {

            onScrolledUp(dy)
        } else if (dy > 0) {
            onScrolledDown(dy)
        }

        if (!recyclerView.canScrollHorizontally(-1)) {

        } else if (!recyclerView.canScrollHorizontally(1)) {
            onScrolledToEnd()
        }
    }

    abstract fun onScrolledToEnd()
    fun onScrolledUp(dy: Int) {
        onScrolledUp()
    }

    fun onScrolledDown(dy: Int) {
        onScrolledDown()
    }

    fun onScrolledUp() {}

    fun onScrolledDown() {}

    fun onScrolledToTop() {}

    companion object {

        var TAG = EndlessOnScrollListener::class.java.simpleName
    }

}