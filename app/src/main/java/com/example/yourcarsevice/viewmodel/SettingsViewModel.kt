package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.yourcarsevice.view.settings.ChangeTheme

class SettingsViewModel(application: Application):AndroidViewModel(application) {

        private val changeTheme = ChangeTheme(application)

    fun changeTheme(){
        changeTheme.changeTheme()
    }
}