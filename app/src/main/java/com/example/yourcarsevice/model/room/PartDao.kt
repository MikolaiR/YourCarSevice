package com.example.yourcarsevice.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PartDao {

    @Insert
    fun insertPart(part: Part): Long

    @Update
    fun updatePart(part: Part)

    @Delete
    fun deletePart(part: Part)

    @Query("select * from parts_table")
    fun getAllPart(): LiveData<List<Part>>

    @Query("DELETE FROM parts_table")
    fun deleteAll()

    @Query("select * from parts_table where backend_id ==:backendId")
    fun getPartBackendId(backendId:String):Part

    @Query("select * from parts_table where _id ==:id")
    fun getPartId(id:String):Part
}