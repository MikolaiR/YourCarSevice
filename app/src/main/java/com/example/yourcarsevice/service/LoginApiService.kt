package com.example.retrofitmvvm.service

import com.example.yourcarsevice.model.retrofit.user.User
import com.example.yourcarsevice.model.retrofit.user.UserTokenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {

    @POST("users")
    fun registrationUser(@Body user: User):Call<ResponseBody>

    @POST("auth/login")
    fun loginUser(@Body user: User): Call<UserTokenResponse>
}