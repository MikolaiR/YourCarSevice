package com.example.yourcarsevice.service

import com.example.yourcarsevice.model.retrofit.party.PartApiResponse
import com.example.yourcarsevice.model.retrofit.user.User
import com.example.yourcarsevice.model.retrofit.user.UserTokenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("users")
    fun registrationUser(@Body user: User):Call<ResponseBody>

    @POST("auth/login")
    fun loginUser(@Body user: User): Call<UserTokenResponse>

    @POST("parts")
    fun synchronizationPart(@Body partApiResponse: PartApiResponse, @Header("Authorization") string: String):Call<ResponseBody>
}