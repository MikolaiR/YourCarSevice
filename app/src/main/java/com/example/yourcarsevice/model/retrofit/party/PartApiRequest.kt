package com.example.yourcarsevice.model.retrofit.party

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PartApiRequest (
    @Json(name = "part_id") val id: String?,
    @Json(name = "part_name") val partName: String?,
    @Json(name = "part_update_date") val partUpdateDate: String?,
    @Json(name = "car_millage") val carMillage: String?,
    @Json(name = "part_comment") val comment: String?,
    @Json(name = "part_price") val price: String?
)