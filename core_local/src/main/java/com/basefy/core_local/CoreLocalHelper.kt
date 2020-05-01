package com.basefy.core_local

import android.location.Location

interface CoreLocalHelper {
    /**
     * Authorization Key'i kaydetmek için kullanılır
     * @param authorizationToken: Kaydedilen authorization key değeridir
     */
    fun saveAuthorizationToken(authorizationToken: String)

    /**
     * Kaydedilen authorization keyi döner
     */
    fun getAuthorizationToken(): String?

    /**
     * User id'yi kaydetmek için kullanılır
     * @param userId: Kaydedilen user id değeLocalModulesridir
     */
    fun saveUserId(userId: String)

    /**
     * Kaydedilen user id'yi döner
     */
    fun getUserId(): String?

    /**
     * Kullanıcının lokasyonunu kaydetmek için kullanılır
     * @param location: Kaydedilmek istenen lokasyondur
     */
    fun saveLocation(location: Location)

    /**
     * Kaydedilen son lokasyonu geri döner
     */
    fun getLocation(): Location

    /**
     * Tüm kaydedilen değerleri silmek için kullanılır
     */
    fun logout()

    /**
     * Telefon numarasını silmek için kullanılır
     * @param phoneNumber: Kaydedilmek istenen telefon numarasıdır
     */
    fun savePhoneNumber(phoneNumber:String?)

    /**
     * Kaydedilen telefon numarasını döner
     */
    fun getPhonenumber(): String

}
