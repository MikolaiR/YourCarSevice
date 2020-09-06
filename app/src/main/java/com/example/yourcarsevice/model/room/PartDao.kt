package com.example.yourcarsevice.model.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PartDao {

    @Insert
    fun insertPart(part: Party): Long

    @Update
    fun updatePart(part: Party)

    @Delete
    fun deletePart(part: Party)

    @Query("select * from parts_table")
    fun getAllPart(): LiveData<List<Party>>

    /*@Query("select * from cars where car_id ==:carId ")
    fun getCar(carId: Long): Car

    @Query("SELECT * FROM cars ORDER BY LOWER(car_model)")
    fun getAllCarsSortModel(): List<Car>

    @Query("SELECT * FROM cars ORDER BY car_year ")
    fun getAllCarsSortYear(): List<Car>

    @Query("SELECT * FROM cars ORDER BY LOWER(car_color)")
    fun getAllCarsSortColor(): List<Car>*/
}