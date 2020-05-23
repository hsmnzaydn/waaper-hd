package com.basefy.base_mvp

import androidx.annotation.LayoutRes
import java.io.Serializable

interface RecyclerItem : Serializable {

    @get:LayoutRes
    val layoutId: Int


}