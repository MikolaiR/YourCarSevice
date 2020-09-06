package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.model.PartRepository
import com.example.yourcarsevice.model.retrofit.party.PartApiResponse
import com.example.yourcarsevice.model.room.Part

class PartItemFragmentViewModel(application: Application):AndroidViewModel(application) {

    private val partRepository = PartRepository(application)

    fun getParts(): LiveData<List<Part>>{
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

    fun updateListResponse(partApiResponse: PartApiResponse){
        partRepository.updateList(partApiResponse)
    }



}