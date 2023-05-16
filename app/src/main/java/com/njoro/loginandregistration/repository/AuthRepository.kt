package com.njoro.loginandregistration.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.njoro.loginandregistration.data.*
import com.njoro.loginandregistration.network.Api
import com.njoro.loginandregistration.network.AuthResponseResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    private var _authToken= MutableLiveData<AuthResponseResult>()
    val authToken: LiveData<AuthResponseResult> = _authToken

    private  val _loginResponse = MutableLiveData<String>()
    val loginResponse: LiveData<String> = _loginResponse

    private var _status =MutableLiveData<AuthResponseResult>()
    val status: LiveData<AuthResponseResult> = _status

   private  var _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    fun login(login: UserLogin){
        Api.retrofitService.loginUser(login).enqueue(loginResults)
    }

    private val loginResults = object :Callback<UserResponse>{
        override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
            Log.e("RESPONSE CODE ",response.code().toString())
            response.body()?.let{ data->

                Log.e("SERVER RESPONSE ",data.message)
                _loginResponse.value = data.status.toString()
                _authToken.value= AuthResponseResult.Status(data.status)
                _authToken.value= AuthResponseResult.Message(data.message)

                if(data.status){
                    _authToken.value = AuthResponseResult.Token(data.token.first())
                }


            }
        }

        override fun onFailure(call: Call<UserResponse>, t: Throwable) {
            t.localizedMessage
            Log.e("RESPONSE ERROR", t.localizedMessage)
        }

    }

    fun registerUser(register: UserRegister) {
        Log.e("POST PARAMS", register.toString())
        Api.retrofitService.registerUser(register).enqueue(registerResults)
    }


    private val registerResults = object : Callback<RegisterResponse> {
        override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse> ) {
            Log.e("Response CODE ", response.code().toString())
            response.body()?.let { data ->
                Log.e("Server Response ", data.toString())
                    _registerResponse.value = data

                    Log.e("Response", data.toString())

            }
        }
        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {

            t.localizedMessage
            Log.e("RESPONSE ERROR", t.localizedMessage)
        }

    }


}