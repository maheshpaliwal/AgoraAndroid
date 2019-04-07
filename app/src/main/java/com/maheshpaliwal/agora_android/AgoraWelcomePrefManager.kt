package com.maheshpaliwal.agora_android

import android.content.Context
import android.content.SharedPreferences

/* To check whether intro has run already or not*/
// responsible for running intro once
class AgoraWelcomePrefManager(internal var context2: Context) {


    internal var pref: SharedPreferences
    internal var editor: SharedPreferences.Editor

    // shared pref mode
    internal var PRIVATE_MODE = 0

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

    init {
        pref = context2.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    companion object {

        // Shared preferences file name
        private val PREF_NAME = "agora-welcome"

        private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    }

}