package com.njoro.loginandregistration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.njoro.ecommerce.utils.SessionManager
import com.njoro.loginandregistration.databinding.ActivityLoginBinding
import com.njoro.loginandregistration.network.AuthResponseResult
import com.njoro.loginandregistration.viewModel.AuthViewModel

class Login : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private lateinit var viewModel: AuthViewModel

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        sessionManager = SessionManager(this)

        _binding!!.apply {
            progressBar.visibility = View.GONE
            btnLogin.setOnClickListener {
                loginNow()
            }
        }




        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        viewModel.authToken.observe(this) { response ->
            when (response) {
                is AuthResponseResult.Message -> {
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
                is AuthResponseResult.Token -> {
                    val user = response.token
                    sessionManager.saveUserDetails(user.userId, user.firstName, user.lastName,
                        user.phoneNumber, user.username, user.email)
                    sessionManager.login()

                }
                is AuthResponseResult.Status -> {
                    if (response.isSuccessful) {
                        gotToProfile()
                    }
                }
                else -> {}
            }
            _binding!!.apply {
                btnLogin.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
            }
        }


    }

    private fun loginNow() {

        _binding!!.apply {
            progressBar.visibility = View.VISIBLE
            btnLogin.visibility = View.GONE

            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (username.isNullOrEmpty()) {
                progressBar.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
                Toast.makeText(this@Login, "Please enter your username", Toast.LENGTH_SHORT).show()
                return
            }
            if (password.isNullOrEmpty()) {
                progressBar.visibility = View.GONE
                btnLogin.visibility = View.VISIBLE
                Toast.makeText(this@Login, "Please enter your password", Toast.LENGTH_SHORT).show()
                return
            }

            viewModel.login(username, password)

        }
    }


    private fun gotToProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }
}