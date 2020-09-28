package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.preference.PreferenceManager
import com.example.yourcarsevice.R
import com.example.yourcarsevice.models.PartRepository
import com.example.yourcarsevice.models.room.Part
import com.example.yourcarsevice.utils.SortList

class PartListFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val partRepository = PartRepository(application)
    private val sortList = SortList()


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

    fun synchronization(parts: List<Part>){
        partRepository.requestUpdatePart(sortList.listPartToMapPartApi(parts))
    }

    fun getPartListResponse(){
        partRepository.responseListPart()
    }

    val theme:LiveData<String> = liveData{
        PreferenceManager.getDefaultSharedPreferences(application).getString(
            application.getString(R.string.theme),
            application.getString(R.string.light_theme_values)
        )!! }
}