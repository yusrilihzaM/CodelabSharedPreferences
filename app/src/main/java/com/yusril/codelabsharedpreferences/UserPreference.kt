package com.yusril.codelabsharedpreferences

import android.content.Context

class UserPreference(context: Context) {
    companion object{
        private const val PREFS_NAME="user_pref"
        private const val NAME="name"
        private const val EMAIL = "email"
        private const val AGE = "age"
        private const val PHONE_NUMBER = "phone"
        private const val LOVE_MU = "islove"
    }

    // inisialisasi prefrence
    private val preferences =context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)

    //masukan ke setuset
    fun setUser(userModel: UserModel){
        val editor=preferences .edit()
        editor.putString(NAME,userModel.name)
        editor.putString(EMAIL,userModel.email)
        editor.putInt(AGE,userModel.age)
        editor.putString(PHONE_NUMBER, userModel.phoneNumber)
        editor.putBoolean(LOVE_MU,userModel.isLove)
        editor.apply()// okey masukan
    }

    fun getUser(): UserModel {
        val model = UserModel()
        model.name = preferences.getString(NAME, "")
        model.email = preferences.getString(EMAIL, "")
        model.age = preferences.getInt(AGE, 0)
        model.phoneNumber = preferences.getString(PHONE_NUMBER, "")
        model.isLove = preferences.getBoolean(LOVE_MU, false)
        return model
    }
}
