package com.example.yourcarsevice.model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.service.RetrofitInstance
import com.example.yourcarsevice.view.BEARER_TOKEN
import com.example.yourcarsevice.view.PREFS_NAME
import com.example.yourcarsevice.model.retrofit.party.PartApiRequest
import com.example.yourcarsevice.model.room.Part
import com.example.yourcarsevice.model.room.PartDao
import com.example.yourcarsevice.model.room.PartDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartRepository(application: Application) {

    private lateinit var partDao: PartDao
    private val sharedPrefs by lazy {
        application.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }


    init {
        val database =
            PartDataBase.InstanceDataBase.getInstance(
                application
            )
        partDao = database.getPartDAO()
    }

    fun getParts(): LiveData<List<Part>> {
        return partDao.getAllPart()
    }

    fun insertPart(part: Part) {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.insertPart(part)
        }
    }

    fun updatePart(part: Part) {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.updatePart(part)
        }
    }

    private fun updateListResponse(partsMap: Map<String,List<PartApiRequest>>) {
        Log.i("addPartResponse", "updateList: ok ")
        val partService = RetrofitInstance().getService()
        val call = partService.synchronizationPart(
            partsMap, "Bearer ${sharedPrefs?.getString(
                BEARER_TOKEN, "error"
            )}"
        )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("addPartResponse", "onFailure: ${t.message} ")
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i("addPartResponse", "onResponse: ${response.errorBody()?.string()} --- ${response.code()} ")
            }
        })
    }

    fun synchronization(partList:List<Part>){
        val requestMap = mutableMapOf<String, List<PartApiRequest>>()
        val deleteList = mutableListOf<PartApiRequest>()
        val updateList = mutableListOf<PartApiRequest>()
        val syncList = mutableListOf<PartApiRequest>()
        for (part in partList) {
            if (part.isDelete) {
                deleteList.add(
                    PartApiRequest(
                        part.backendId,
                        null, null, null, null, null
                    )
                )
            }
            Log.i("synchronization", "deleteList: $deleteList")
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
            if (!part.isSync){
                syncList.add( PartApiRequest(
                    part.backendId,
                    part.partName,
                    part.partUpdateDate,
                    part.carMillage,
                    part.comment,
                    part.price
                ))
                part.isSync = true
            }
            Log.i("synchronization", "syncList: $syncList")
        }
        requestMap["delete"] = deleteList
        requestMap["update"] = updateList
        requestMap["sync"] = syncList
        Log.i("synchronization", "synchronization: ${requestMap["delete"]}")
        updateListResponse(requestMap)
    }
}

