package com.example.yourcarsevice.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.example.yourcarsevice.R

class SettingsFragmentsViewModel(application: Application):AndroidViewModel(application) {

private var _theme: MutableLiveData<String> = MutableLiveData(
    PreferenceManager.getDefaultSharedPreferences(application).getString(
    application.getString(R.string.theme),
    application.getString(R.string.light_theme_values)
)!!)
    val theme:LiveData<String> = _theme
}
