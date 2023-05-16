package com.njoro.ecommerce.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class SessionManager(context: Context) {

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val PREF_NAME = "MyAppPref"

          const val KEY_USER_ID = "userId"
          const val KEY_USER_FIRST_NAME = "firstName"
          const val KEY_USER_LAST_NAME = "lastName"
          const val KEY_USER_PHONE_NUMBER = "phoneNumber"
          const val KEY_USER_NAME = "userName"
          const val KEY_USER_EMAIL = "userEmail"

        @Volatile
        private var instance: SessionManager? = null

        fun getInstance(context: Context): SessionManager {
            return instance ?: synchronized(this) {
                instance ?: SessionManager(context).also { instance = it }
            }
        }

    }

    private val preferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun saveUserDetails(userId: String,firstName:String,lastName:String,phoneNumber:String,
                        userName: String, userEmail: String) {
        val editor = preferences.edit()
        editor.putString(KEY_USER_ID, userId)
        editor.putString(KEY_USER_FIRST_NAME, firstName)
        editor.putString(KEY_USER_LAST_NAME, lastName)
        editor.putString(KEY_USER_PHONE_NUMBER, phoneNumber)
        editor.putString(KEY_USER_NAME, userName)
        editor.putString(KEY_USER_EMAIL, userEmail)
        editor.apply()
    }

    fun getUserDetails(): HashMap<String, String> {
        val user = HashMap<String, String>()
        user[KEY_USER_ID] = preferences.getString(KEY_USER_ID, "")!!
        user[KEY_USER_NAME] = preferences.getString(KEY_USER_NAME, "")!!
        user[KEY_USER_FIRST_NAME] = preferences.getString(KEY_USER_FIRST_NAME, "")!!
        user[KEY_USER_LAST_NAME] = preferences.getString(KEY_USER_LAST_NAME, "")!!
        user[KEY_USER_PHONE_NUMBER] = preferences.getString(KEY_USER_PHONE_NUMBER, "")!!
        user[KEY_USER_NAME] = preferences.getString(KEY_USER_NAME, "")!!
        user[KEY_USER_EMAIL] = preferences.getString(KEY_USER_EMAIL, "")!!
        return user
    }
    fun login() {
        preferences.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()
    }

    fun logout() {
        preferences.edit().putBoolean(KEY_IS_LOGGED_IN, false).apply()
    }
    fun isLoggedIn(): Boolean{
        return preferences.getBoolean(KEY_IS_LOGGED_IN, false)

    }

}
