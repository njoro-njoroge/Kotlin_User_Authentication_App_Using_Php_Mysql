package com.njoro.loginandregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.njoro.loginandregistration.databinding.ActivityRegisterBinding
import com.njoro.loginandregistration.viewModel.AuthViewModel

class Register : AppCompatActivity() {
    private var _binding : ActivityRegisterBinding? = null
    private lateinit var viewModel: AuthViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this,R.layout.activity_register)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]
//
//        _binding!!.viewModel = viewModel

        viewModel.registerResponse.observe(this) { response ->
            _binding!!.apply {
                progressBar.visibility=View.GONE
                btnRegister.visibility=View.VISIBLE
            }
            if(response.status == true) {
                goToLogin()
            }
            Toast.makeText(this@Register,response.message,Toast.LENGTH_SHORT).show()
        }
        _binding!!.apply {
            progressBar.visibility=View.GONE

            btnRegister.setOnClickListener {
                registerUser()
            }
        }

    }
    private fun registerUser(){
        _binding!!.apply {
            progressBar.visibility=View.VISIBLE
//            btnRegister.visibility=View.GONE

           val firstName = edtFirstName.text.toString().trim()
            val lastName = edtLastName.text.toString().trim()
            val username = edtUsername.text.toString().trim()
            val phoneNumber= edtPhoneNumber.text.toString().trim()
            val email = edtEmail.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if(firstName.isNullOrEmpty()){
                Toast.makeText(this@Register,"First name is required",Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                btnRegister.visibility=View.VISIBLE
                return
            }
            if(lastName.isNullOrEmpty()){
                Toast.makeText(this@Register,"Last name is required",Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                btnRegister.visibility=View.VISIBLE
                return
            }
            if(username.isNullOrEmpty()){
                Toast.makeText(this@Register,"Username is required",Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                btnRegister.visibility=View.VISIBLE
                return
            }
            if(phoneNumber.isNullOrEmpty()){
                Toast.makeText(this@Register,"Phone number is required",Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                btnRegister.visibility=View.VISIBLE
                return
            }
            if(email.isNullOrEmpty()){
                Toast.makeText(this@Register,"Email is required",Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                btnRegister.visibility=View.VISIBLE
                return
            }
            if(password.isNullOrEmpty()){
                Toast.makeText(this@Register,"Password is required",Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
                btnRegister.visibility=View.VISIBLE
                return
            }

            viewModel.register(firstName, lastName, username, phoneNumber, email, password)
        }


    }
    private fun goToLogin(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}