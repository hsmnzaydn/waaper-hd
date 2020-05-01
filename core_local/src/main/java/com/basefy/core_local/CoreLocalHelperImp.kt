package com.basefy.core_local

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import com.google.gson.Gson
import javax.inject.Inject

class CoreLocalHelperImp
@Inject constructor(
    private val context: Context,
    private val gson: Gson
) : CoreLocalHelper {


    private val AUTHORIZATON_PREF_HELPER = "authorizationPref"
    private val USER_ID_PREF_HELPER = "userIdPref"
    private val PREF_KEY_LOCATION = "PREF_KEY_LOCATION"
    private val PREF_KEY_PHONE_NUMBER = "PREF_KEY_PHONE_NUMBER"

    lateinit var mPrefs: SharedPreferences

    init {
        mPrefs = context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
    }

    override fun saveLocation(location: Location) {

        mPrefs.edit().putString(PREF_KEY_LOCATION, gson.toJson(location))
    }

    override fun getLocation(): Location {
        return gson.fromJson(mPrefs.getString(PREF_KEY_LOCATION, "")!!, Location::class.java)
    }

    override fun saveAuthorizationToken(authorizationToken: String) {
        mPrefs.edit().putString(AUTHORIZATON_PREF_HELPER, "Bearer $authorizationToken").apply()
    }

    override fun getAuthorizationToken(): String {
        return mPrefs.getString(AUTHORIZATON_PREF_HELPER, "UnAuthorization")!!
    }

    override fun saveUserId(userId: String) {
        mPrefs.edit().putString(USER_ID_PREF_HELPER, userId).apply()
    }

    override fun getUserId(): String? {
        return mPrefs.getString(USER_ID_PREF_HELPER, null)
    }

    override fun logout() {
        mPrefs.edit().clear().apply()
    }


    override fun savePhoneNumber(phoneNumber: String?) {
        mPrefs.edit().putString(PREF_KEY_PHONE_NUMBER, phoneNumber).apply()
    }

    override fun getPhonenumber(): String {
        return mPrefs.getString(PREF_KEY_PHONE_NUMBER, "905457878383")!!
    }

}