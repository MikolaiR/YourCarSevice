package com.example.yourcarsevice.utils

import android.util.Log
import com.example.yourcarsevice.model.retrofit.party.PartApiRequest
import com.example.yourcarsevice.model.room.Part

class SortList {

    fun listPartToMapPartApi(partList: List<Part>): Map<String, List<PartApiRequest>> {
        val requestMap = mutableMapOf<String, List<PartApiRequest>>("create" to listOf(), "remove" to listOf(), "update" to listOf())
        val removeList = mutableListOf<PartApiRequest>()
        val updateList = mutableListOf<PartApiRequest>()
        val syncList = mutableListOf<PartApiRequest>()
        val listIsSync = mutableListOf<Part>()
        for (part in partList) {
            Log.i("addPartResponse", "partList:${part.partName} delete ${part.isDelete}")
            if (part.isDelete) {
                Log.i("addPartResponse", "partList:${part.partName} delete ${part.isDelete}")
                removeList.add(
                    PartApiRequest(
                        part.backendId,
                        null, null, null, null, null
                    )
                )
            }
            Log.i("addPartResponse", "partList:${part.partName} isUpdate ${part.isUpdate}")
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
            Log.i("synchronization", "updateList: $updateList")
            if (!part.isSync) {
                listIsSync.add(part)
                Log.i("addPartResponse", "partList: ${part.partName} sync ${part.isSync} ")
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
            Log.i("synchronization", "syncList: $syncList")
        }
        requestMap["remove"] = removeList
        requestMap["update"] = updateList
        requestMap["create"] = syncList
        return requestMap
    }
}