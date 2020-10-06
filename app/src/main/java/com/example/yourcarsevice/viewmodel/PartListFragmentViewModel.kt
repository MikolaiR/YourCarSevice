package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.models.PartRepository
import com.example.yourcarsevice.models.room.Part

class PartListFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val partRepository = PartRepository(application)

    fun getPartBackendID(backendID:String):Part{
        return partRepository.getPartBackendId(backendID)
    }

    fun getParts(): LiveData<List<Part>> {
        return partRepository.getParts()
    }

    fun addNewPart(part: Part){
        partRepository.insertPart(part)
    }
    fun deletePart(part: Part){
        partRepository.deletePart(part)
    }

    fun updatePart(part: Part){
        partRepository.updatePart(part)
    }

    fun synchronization(list: List<Part>){
        partRepository.requestUpdatePart(list)
    }

    fun getPartListResponse(){
        partRepository.responseListPart()
    }

    fun getListNotDelete(list: List<Part>):List<Part>{
        return partRepository.getListNotDelete(list)
    }
}