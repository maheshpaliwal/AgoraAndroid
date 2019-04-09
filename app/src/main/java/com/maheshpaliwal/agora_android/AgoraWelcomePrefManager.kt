package com.maheshpaliwal.agora_android

import android.content.Context
import android.content.SharedPreferences

/* To check whether intro has run already or not*/
// responsible for running intro once
class AgoraWelcomePrefManager(internal var context2: Context) {

    // variable SharedPreference
    private var pref: SharedPreferences
    // shared preference editor
    private var editor: SharedPreferences.Editor
    // shared pref mode
    private var PRIVATE_MODE = 0
  // if First time launch is true then store true  inside editor
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