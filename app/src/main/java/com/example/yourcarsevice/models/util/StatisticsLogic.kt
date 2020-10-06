package com.example.yourcarsevice.models.util

import com.example.yourcarsevice.models.room.Part

class StatisticsLogic {

    fun getTotalAmount(list: List<Part>): Double {
        var outPrice = 0.0
        list.forEach { outPrice += it.price?.toDouble()!! }
        return outPrice
    }

    fun getTotalMillage(list: List<Part>): Int {
        var startMillage = list[0].carMillage?.toInt()!!
        var endMillage = 0
        list.forEach {
            if (it.carMillage?.toInt()!! < startMillage) {
                startMillage = it.carMillage?.toInt()!!
            }
            if (it.carMillage?.toInt()!! > endMillage) {
                endMillage = it.carMillage?.toInt()!!
            }
        }
        return endMillage - startMillage
    }

    fun getKilometerOfTravel(list: List<Part>):Double{
        val millage = getTotalMillage(list)
        val totalAmount = getTotalAmount(list)
        return totalAmount / millage
    }
}