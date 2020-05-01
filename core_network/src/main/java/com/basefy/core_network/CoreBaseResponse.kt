package com.basefy.core_network

class CoreBaseResponse<T> {

    var code: Int = 0
        private set
    var data: T? = null
        private set

    var error: String? = null
        private set

    fun setCode(code: Int): CoreBaseResponse<T> {
        this.code = code
        return this
    }

    fun setData(data: T?): CoreBaseResponse<T> {
        this.data = data
        return this
    }

    fun setError(error: String): CoreBaseResponse<T> {
        this.error = error
        return this
    }

}

