package com.example.yourcarsevice.model

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.retrofitmvvm.service.RetrofitInstance
import com.example.yourcarsevice.fragment.BEARER_TOKEN
import com.example.yourcarsevice.fragment.PREFS_NAME
import com.example.yourcarsevice.model.retrofit.party.PartApiResponse
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

    fun deletePart(part: Part) {
        GlobalScope.launch(Dispatchers.IO) {
            partDao.deletePart(part)
        }
    }

    fun updateList(part: PartApiResponse) {
        val partService = RetrofitInstance().getService()
        val call = partService.synchronizationPart(
            part, "Bearer ${sharedPrefs?.getString(
                BEARER_TOKEN, "error"
            )!!}"
        )
        call.enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }
        })
    }
}

