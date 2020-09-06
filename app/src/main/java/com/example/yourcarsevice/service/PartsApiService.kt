package com.example.yourcarsevice.service

import retrofit2.http.POST

interface PartsApiService{

    @POST("parts")
    fun synchronizationPart( )
}