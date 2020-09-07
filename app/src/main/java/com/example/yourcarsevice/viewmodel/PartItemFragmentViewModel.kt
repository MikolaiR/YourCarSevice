package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.model.PartRepository
import com.example.yourcarsevice.model.room.Part

class PartItemFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val partRepository = PartRepository(application)

    fun getParts(): LiveData<List<Part>> {
        return partRepository.getParts()
    }

    fun addNewPart(part: Part){
        partRepository.insertPart(part)
    }
    fun deletePart(part: Part){
        part.isDelete = true
    }

    fun updatePart(part: Part){
        part.isUpdate = true
        partRepository.updatePart(part)
    }

    fun synchronization(parts: List<Part>){
        partRepository.synchronization(parts)
    }
}