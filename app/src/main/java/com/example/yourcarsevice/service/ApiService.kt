package com.example.yourcarsevice.service

import com.example.yourcarsevice.models.retrofit.party.PartApiRequest
import com.example.yourcarsevice.models.retrofit.party.PartApiResponse
import com.example.yourcarsevice.models.retrofit.user.User
import com.example.yourcarsevice.models.retrofit.user.UserTokenResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

@JvmSuppressWildcards
interface ApiService {
    @POST("users")
    fun registrationUser(@Body user: User): Call<ResponseBody>

    @POST("auth/login")
    fun loginUser(@Body user: User): Call<UserTokenResponse>

    @POST("parts/sync")
    fun synchronizationPart(@Body partApiResponseMap: Map<String, List<PartApiRequest>>, @Header("Authorization") string: String): Call<ResponseBody>

    @GET("parts")
    fun getAllParts(@Header("Authorization") string: String): Call<List<PartApiResponse>>
}