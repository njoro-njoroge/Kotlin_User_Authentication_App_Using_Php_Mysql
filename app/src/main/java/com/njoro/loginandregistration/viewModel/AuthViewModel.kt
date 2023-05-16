package com.njoro.loginandregistration.viewModel

import androidx.lifecycle.ViewModel
import com.njoro.loginandregistration.data.UserLogin
import com.njoro.loginandregistration.data.UserRegister
import com.njoro.loginandregistration.repository.AuthRepository

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()
    val authToken = repository.authToken
    val status = repository.status
    val loginResponse = repository.loginResponse
    val registerResponse = repository.registerResponse


    fun login(username: String, password: String) {
        repository.login(UserLogin(username, password))
    }

    fun register(
        firstName: String, lastName: String, username: String,
        phoneNumber: String, email: String, password: String
    ) {

        repository.registerUser(
            UserRegister(
                firstName,
                lastName,
                email,
                username,
                phoneNumber,
                password
            )
        )
    }



}