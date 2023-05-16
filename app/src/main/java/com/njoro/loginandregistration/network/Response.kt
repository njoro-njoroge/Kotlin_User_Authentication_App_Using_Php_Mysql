package com.njoro.loginandregistration.network

import com.njoro.loginandregistration.data.UserToken

sealed class AuthResponseResult{
    data class Token (val token: UserToken): AuthResponseResult()
    data class Status (val isSuccessful: Boolean): AuthResponseResult()
    data class Message (val message: String) : AuthResponseResult()
    data class Progress (val isLoading: Boolean): AuthResponseResult()
}
