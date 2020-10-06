package com.example.yourcarsevice.models

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.yourcarsevice.view.authorization.PREFS_NAME

class SharedPreferencesInstance(application: Application) {

    val sharedPrefs: SharedPreferences by lazy {
        application.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }
}