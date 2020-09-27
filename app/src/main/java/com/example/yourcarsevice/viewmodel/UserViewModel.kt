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

    fun getTokenUser(user: User) {
        userRepository.loginUser(user)
    }

    fun registrationAndGetToken(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
           async {
                userRepository.registrationUser(user)
            }.await()
            userRepository.loginUser(user)
        }
    }
}