package com.example.yourcarsevice.view.settings

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.yourcarsevice.R

const val KEY_THEME = "prefs.theme"
const val THEME_UNDEFINED = -1
const val THEME_LIGHT = 0
const val THEME_DARK = 1

class ChangeTheme(private val application: Application) {

    private val sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application.applicationContext)
    fun changeTheme() {
        when (sharedPref.getString(application.resources.getString(R.string.theme), application.resources.getString(R.string.light_theme))) {
            application.resources.getString(R.string.dark_theme) -> sharedPref.edit().putInt(KEY_THEME, THEME_DARK).apply()
            application.resources.getString(R.string.light_theme) -> sharedPref.edit().putInt(KEY_THEME, THEME_LIGHT).apply()
        }
    }
}