package com.example.yourcarsevice.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "parts_table")
data class Party(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Int,
    @ColumnInfo(name = "part_name") val partName: String,
    @ColumnInfo(name = "part_update_date") val partUpdateDate: String,
    @ColumnInfo(name = "car_millage") val carMillage: Int,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "comment") val comment: String
)