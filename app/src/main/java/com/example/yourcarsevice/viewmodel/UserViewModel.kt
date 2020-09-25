package com.example.yourcarsevice.viewmodel

import android.app.Application
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.example.yourcarsevice.model.retrofit.user.User
import com.example.yourcarsevice.model.UserRepository
import kotlinx.coroutines.*

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application)

    fun getTokenUser(user: User, fragment: Fragment, registration:Boolean) {
        userRepository.loginUser(user,fragment,registration)
    }

    fun registrationAndGetToken(user: User, fragment: Fragment, registration:Boolean) {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Default) {
                userRepository.registrationUser(user)
            }
            Log.i("loginUser", "registrationAndGetToken: $user")
            userRepository.loginUser(user,fragment,registration)
        }
    }
}