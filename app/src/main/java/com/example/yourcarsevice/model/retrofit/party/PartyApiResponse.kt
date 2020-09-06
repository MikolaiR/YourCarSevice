package com.example.yourcarsevice.model.retrofit.party

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class PartyApiResponse (
    @Json(name = "id") val id: Int,
    @Json(name = "part_name") val partName: String,
    @Json(name = "part_update_date") val partUpdateDate: String,
    @Json(name = "car_millage") val carMillage: Int,
    @Json(name = "comment") val comment: String,
    @Json(name = "price") val price: Int
)