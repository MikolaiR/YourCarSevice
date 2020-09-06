package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.model.PartRepository
import com.example.yourcarsevice.model.room.Party

class PartItemFragmentViewModel(application: Application):AndroidViewModel(application) {

    private val partRepository = PartRepository(application)

    fun getParts(): LiveData<List<Party>>{
        return partRepository.getParts()
    }

    fun addNewPart(part: Party){
        partRepository.insertPart(part)
    }
    fun deletePart(part: Party){
        partRepository.deletePart(part)
    }

    fun updatePart(part: Party){
        partRepository.updatePart(part)
    }


}