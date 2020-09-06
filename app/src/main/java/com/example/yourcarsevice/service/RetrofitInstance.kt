package com.example.retrofitmvvm.service

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "http://fathomless-ocean-27361.herokuapp.com/"

class RetrofitInstance {
        private var retrofit: Retrofit? = null
        fun getService():LoginApiService{
            if (retrofit == null){
                Log.i("result", "getService: ON")
                retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit?.create(LoginApiService::class.java)!!
        }
}