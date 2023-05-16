package com.njoro.loginandregistration.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.njoro.loginandregistration.data.RegisterResponse
import com.njoro.loginandregistration.data.UserLogin
import com.njoro.loginandregistration.data.UserRegister
import com.njoro.loginandregistration.data.UserResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


private const val BASE_URL = "http://192.168.0.112/hustle_free/api_endpoint/login_register_api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ServiceApi {
    @POST("login.php")
    fun loginUser(@Body user: UserLogin): Call<UserResponse>

    @POST("register.php")
     fun registerUser(@Body user: UserRegister): Call<RegisterResponse>
}
object Api {
    val retrofitService: ServiceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }
}