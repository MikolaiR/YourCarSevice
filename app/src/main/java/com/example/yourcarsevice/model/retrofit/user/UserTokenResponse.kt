package com.example.yourcarsevice.model.retrofit.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserTokenResponse(
    @Json(name = "Bearer") val bearer: String
)

