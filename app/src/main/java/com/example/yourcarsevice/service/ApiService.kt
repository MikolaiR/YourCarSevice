package com.example.yourcarsevice.service

import com.example.yourcarsevice.model.retrofit.party.PartApiRequest
import com.example.yourcarsevice.model.retrofit.user.User
import com.example.yourcarsevice.model.retrofit.user.UserTokenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("users")
    fun registrationUser(@Body user: User):Call<ResponseBody>

    @POST("auth/login")
    fun loginUser(@Body user: User): Call<UserTokenResponse>

    @POST("parts")
    fun synchronizationPart(@Body partApiResponseMap: Map<String,List<PartApiRequest>>, @Header("Authorization") string: String):Call<ResponseBody>


}