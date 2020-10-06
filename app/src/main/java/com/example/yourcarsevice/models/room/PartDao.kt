package com.example.yourcarsevice.models.room

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

    @Query("delete from parts_table where is_delete = 1")
    fun removeDeletedPart()

    @Query("select * from parts_table")
    fun getAllPart(): LiveData<List<Part>>

    @Query("DELETE FROM parts_table")
    fun deleteAll()

    @Query("select * from parts_table where backend_id ==:backendId")
    fun getPartBackendId(backendId:String):Part

    @Query("select * from parts_table where is_delete = 1")
    fun getPartsIsDelete():List<Part>

    @Query("select * from parts_table where is_update = 1")
    fun getPartsIsUpdate():List<Part>

    @Query("select * from parts_table where is_sync = 1")
    fun getPartsIsSync():List<Part>
}