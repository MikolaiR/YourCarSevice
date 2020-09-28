package com.example.yourcarsevice.models.retrofit.user

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserTokenResponse(
    @Json(name = "Bearer") val bearer: String
)

