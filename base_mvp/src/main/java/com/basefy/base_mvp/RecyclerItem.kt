package com.basefy.base_mvp

import androidx.annotation.LayoutRes
import java.io.Serializable

open class RecyclerItem constructor(@LayoutRes val layoutId: Int, open var comporeId: String)
