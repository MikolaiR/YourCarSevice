package com.example.yourcarsevice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.models.PartRepository
import com.example.yourcarsevice.models.room.Part
import com.example.yourcarsevice.util.StatisticsLogic

class StatisticsPartViewModel(application: Application) : AndroidViewModel(application) {

    private val partRepository = PartRepository(application)
    private val statisticsLogic = StatisticsLogic()


    fun getParts(): LiveData<List<Part>> {
        return partRepository.getParts()
    }

    fun getTotalAmount(list: List<Part>): Double {
      return  statisticsLogic.getTotalAmount(list)
    }

    fun getTotalMillage(list: List<Part>):Int{
        return statisticsLogic.getTotalMillage(list)
    }
    fun getCoastKilometerOfTravel(list: List<Part>):Double{
      return  statisticsLogic.getKilometerOfTravel(list)
    }
}