package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.preference.PreferenceManager
import com.example.yourcarsevice.R

class MainActivityViewModel(application: Application):AndroidViewModel(application) {

    val theme:LiveData<String> = liveData{PreferenceManager.getDefaultSharedPreferences(application).getString(
        application.getString(R.string.theme),
        application.getString(R.string.light_theme_values)
    )!! }

}