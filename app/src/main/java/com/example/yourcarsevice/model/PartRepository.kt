package com.example.yourcarsevice.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.model.room.Party
import com.example.yourcarsevice.model.room.PartDao
import com.example.yourcarsevice.model.room.PartDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PartRepository(application: Application) {

    private lateinit var partDao: PartDao

    private lateinit var parts: LiveData<List<Party>>


    init {
        val database =
            PartDataBase.InstanceDataBase.getInstance(
                application
            )
        partDao = database.getPartDAO()

    }

    fun getParts(): LiveData<List<Party>> {
        return partDao.getAllPart()
    }

    fun insertPart(part: Party) {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.insertPart(part)
        }
    }

    fun updatePart(part: Party) {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.updatePart(part)
        }
    }

    fun deletePart(part: Party) {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.deletePart(part)
        }
    }

}

