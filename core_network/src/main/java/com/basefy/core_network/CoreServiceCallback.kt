package com.basefy.core_network

interface CoreServiceCallback<T> {
    /**
     * Servisten gelen herhangi bir veriyi ön tarafa aktarmak için kullanılır
     * @param response: Servisten gelen veridir
     */
    fun onSuccess(response:T?)

    /**
     * Servisten veri gelince alınan herhangi bir hatayı ön tarafa taşımak için kullanılır
     * @param errorCode: Servisten gelen hata kodudur
     * @param errorMessage: Servisten gelen hata mesajıdır
     */
    fun onError(errorCode:Int,errorMessage:String)
}