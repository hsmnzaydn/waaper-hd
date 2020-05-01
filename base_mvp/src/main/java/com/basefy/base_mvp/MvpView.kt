package com.basefy.base_mvp

import android.app.Activity
import android.graphics.Color


interface MvpView {

    /**
     * Uygulamada loading göstermek amacıyla kullanılır
     */
    fun showLoading()

    /**
     * Uygulamada eğer açık olan bir loading var ise bu loadingi kapatır
     */
    fun hideLoading()

    /**
     * Kullanıcıya herhangi bir mesaj göstermek için kullanılır
     * @param text: Gösterilecek mesaj
     */
    fun showInformation(text: String)

    /**
     * Kullanıcıya herhangi bir hata mesajı göstermek için kullanılır
     * @param text: Gösterilecek mesaj
     */
    fun showError(text: String)

    /**
     * O an ki ekranı fullscren yapmak için kullanılır
     */
    fun hideSystemUI()

    /**
     * Kullanıcıya dialog göstermek amacıyla kullanılır
     * @param title: Dialog başlıkta gözükecek texttir
     * @param description: Dialog descriptionda gözükecek texttir
     * @param negativeButtonText: Dialog negatif kısımda gözükecek butonun textidir
     * @param onClickNegativeButton: Negatif butona basınca tetiklenecek listenerdır
     */
    fun showDialogWithOutChoose(
        title: String,
        description: String,
        negativeButtonText: String?,
        onClickNegativeButton: () -> Unit
    )


    /**
     * Full screen ekranın scroll olabilmesi için kullanılır
     * @param statusBarColor: Status bar rengini vermek için kullanılır
     */
    fun addScrollableToFullScreenView(statusBarColor: Int? = Color.TRANSPARENT)


     fun initUI() {

     }
     fun againOpened() {

     }
}