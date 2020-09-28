package com.example.yourcarsevice.utils

import android.util.Log
import com.example.yourcarsevice.models.retrofit.party.PartApiRequest
import com.example.yourcarsevice.models.room.Part

class SortList {

    fun listPartToMapPartApi(partList: List<Part>): Map<String, List<PartApiRequest>> {
        val requestMap = mutableMapOf<String, List<PartApiRequest>>("create" to listOf(), "remove" to listOf(), "update" to listOf())
        val removeList = mutableListOf<PartApiRequest>()
        val updateList = mutableListOf<PartApiRequest>()
        val syncList = mutableListOf<PartApiRequest>()
        val listIsSync = mutableListOf<Part>()
        for (part in partList) {
            if (part.isDelete) {
                removeList.add(
                    PartApiRequest(
                        part.backendId,
                        null, null, null, null, null
                    )
                )
            }
            if (part.isUpdate) {
                updateList.add(
                    PartApiRequest(
                        part.backendId,
                        part.partName,
                        part.partUpdateDate,
                        part.carMillage,
                        part.comment,
                        part.price
                    )
                )
            }
            if (!part.isSync) {
                listIsSync.add(part)
                syncList.add(
                    PartApiRequest(
                        part.backendId,
                        part.partName,
                        part.partUpdateDate,
                        part.carMillage,
                        part.comment,
                        part.price
                    )
                )
            }
        }
        Log.d("listPartToMapPartApi", "remove: ${removeList.size} ")
        Log.d("listPartToMapPartApi", "update: ${updateList.size} ")
        Log.d("listPartToMapPartApi", "create: ${syncList.size} ")
        requestMap["remove"] = removeList
        requestMap["update"] = updateList
        requestMap["create"] = syncList
        return requestMap
    }
}