package com.example.yourcarsevice.models.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Part::class], version = 1)
abstract class PartDataBase : RoomDatabase() {

    abstract fun getPartDAO(): PartDao

    object InstanceDataBase{

        private var instance: PartDataBase? = null

        fun getInstance(context: Context): PartDataBase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context.applicationContext,
                        PartDataBase::class.java, "partDB")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance as PartDataBase
        }
    }


}

