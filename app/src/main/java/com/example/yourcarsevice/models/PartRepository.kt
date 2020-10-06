package com.example.yourcarsevice.models

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.yourcarsevice.models.retrofit.RetrofitInstance
import com.example.yourcarsevice.view.authorization.BEARER_TOKEN
import com.example.yourcarsevice.view.authorization.PREFS_NAME
import com.example.yourcarsevice.models.retrofit.party.PartApiResponse
import com.example.yourcarsevice.models.room.Part
import com.example.yourcarsevice.models.room.PartDao
import com.example.yourcarsevice.models.room.PartDataBase
import com.example.yourcarsevice.utils.SortList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartRepository(application: Application) {

    private val sortList = SortList()
    private val partDao: PartDao = PartDataBase.InstanceDataBase.getInstance(application).getPartDAO()
    private val retrofitInstance = RetrofitInstance().getService()
    private val sharedPrefs by lazy {
        application.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun getPartBackendId(backendId: String): Part {
        return partDao.getPartBackendId(backendId)
    }

    fun deleteAll() {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.deleteAll()
        }
    }

    fun deletePart(part: Part) {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.deletePart(part)
        }
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

    fun getListNotDelete(list: List<Part>): List<Part> {
        return sortList.getListNotDelete(list)
    }

    fun responseListPart() {
        val call = retrofitInstance.getAllParts(
            "Bearer ${
                sharedPrefs?.getString(
                    BEARER_TOKEN, "error"
                )
            }"
        )
        call.enqueue(object : Callback<List<PartApiResponse>> {
            override fun onFailure(call: Call<List<PartApiResponse>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<PartApiResponse>>, response: Response<List<PartApiResponse>>) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        deleteAll()
                        val partApi = response.body()
                        partApi?.forEach {
                            val part = Part()
                            part.backendId = it.id
                            part.partUpdateDate = it.partUpdateDate
                            part.partName = it.partName
                            part.carMillage = it.carMillage
                            part.price = it.price
                            part.isSync = true
                            part.comment = it.comment
                            insertPart(part)
                        }
                    }
                }
            }
        })
    }

    fun requestUpdatePart(list: List<Part>) {
        val partsMap = sortList.listPartToMapPartApi(list)
        val partService = RetrofitInstance().getService()
        val call = partService.synchronizationPart(
            partsMap, "Bearer ${
                sharedPrefs?.getString(
                    BEARER_TOKEN, "error"
                )
            }"
        )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val syncList = partsMap["create"]
                    GlobalScope.launch(Dispatchers.IO) {
                        syncList?.forEach {
                            val partSync = getPartBackendId(it.id!!)
                            partSync.isSync = true
                            updatePart(partSync)
                            partDao.removeDeletedPart()
                        }
                    }
                }
            }
        })
    }
}

