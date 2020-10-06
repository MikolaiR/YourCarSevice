package com.example.yourcarsevice.models.retrofit

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://fathomless-ocean-27361.herokuapp.com/"

class RetrofitInstance {
        private var retrofit: Retrofit? = null
        fun getService(): ApiService {
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
            }
            return retrofit?.create(ApiService::class.java)!!
        }
}