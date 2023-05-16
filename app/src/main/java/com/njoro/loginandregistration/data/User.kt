package com.njoro.loginandregistration.data

import com.squareup.moshi.Json


data class UserResponse(
    val status: Boolean,
    val message: String,
    @Json(name = "userToken")
    val token:  List<UserToken>
)
data class UserToken(
    val userId: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val username: String,
    val phoneNumber: String,
)

data class UserLogin(
    val username: String,
    val password: String
)

data class RegisterResponse(
    val status: Boolean,
    val message: String
)

data class UserRegister(
    val firstName:String,
    val lastName: String,
    val email: String,
    val username: String,
    val phoneNumber: String,
    val password: String
)