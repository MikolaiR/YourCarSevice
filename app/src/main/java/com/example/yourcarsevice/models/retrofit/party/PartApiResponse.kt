package com.example.yourcarsevice.models.retrofit.party

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PartApiResponse (
    @Json(name = "_id") val id: String?,
    @Json(name = "name") val partName: String?,
    @Json(name = "updateDate") val partUpdateDate: String?,
    @Json(name = "carMillage") val carMillage: String?,
    @Json(name = "comment") val comment: String?,
    @Json(name = "price") val price: String?
)