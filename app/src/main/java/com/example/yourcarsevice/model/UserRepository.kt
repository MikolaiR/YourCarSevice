package com.example.yourcarsevice.model

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.yourcarsevice.R
import com.example.yourcarsevice.service.RetrofitInstance
import com.example.yourcarsevice.view.authorization.BEARER_TOKEN
import com.example.yourcarsevice.view.authorization.PREFS_NAME
import com.example.yourcarsevice.model.retrofit.user.User
import com.example.yourcarsevice.model.retrofit.user.UserTokenResponse
import com.example.yourcarsevice.view.PartActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(val application: Application) {

    private val sharedPrefs by lazy {
        application.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    fun loginUser(user: User) {
        val loginService = RetrofitInstance().getService()
        val call = loginService.loginUser(user)
        call.enqueue(object : Callback<UserTokenResponse> {
            override fun onFailure(call: Call<UserTokenResponse>, t: Throwable) {
                Toast.makeText(application, " ${t.message}", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<UserTokenResponse>, response: Response<UserTokenResponse>) {
                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        userResponse.bearer
                        sharedPrefs?.edit()?.putString(BEARER_TOKEN, userResponse.bearer)?.apply()
                        application.startActivity(Intent(application,PartActivity::class.java))
                        /*// todo get token and fragment to part
                        if (registration){
                            NavHostFragment.findNavController(fragment).navigate(R.id.action_registrationFragment_to_partFragment)
                        }else{
                            NavHostFragment.findNavController(fragment).navigate(R.id.action_loginFragment_to_partFragment)
                        }*/

                    }
                } else {
                    val str = separatorForErrorMessenger(response.errorBody()?.string()!!)
                    Toast.makeText(application, str, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun registrationUser(user: User):Boolean {
        var isSuccessful = false
        val loginService = RetrofitInstance().getService()
        val call = loginService.registrationUser(user)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.i("loginUser", "onResponse: isSuccessful ")
                    isSuccessful = true
                } else {
                    val str = separatorForErrorMessenger(response.errorBody()?.string()!!)
                    Log.i("loginUser", "onResponse: $str ")
                    Toast.makeText(application, str, Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("loginUser", "onFailure: ${t.message}")
                Toast.makeText(application, "${t.message}", Toast.LENGTH_LONG).show()
            }
        })
        return isSuccessful
    }
}

fun separatorForErrorMessenger(errorMessage: String): String {
    var index = 0
    for (it in errorMessage) {
        if (it != ':') {
            index++
        } else {
            break
        }
    }
    return errorMessage.substring(index + 2, errorMessage.lastIndex - 1)
}