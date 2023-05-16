package com.njoro.loginandregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.njoro.ecommerce.utils.SessionManager
import com.njoro.loginandregistration.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sessionManager = SessionManager(this)
        if(sessionManager.isLoggedIn()){
            gotToProfile()
            Toast.makeText(this," You are logged in",Toast.LENGTH_SHORT).show()
            finish()
        }else{
            Toast.makeText(this," Please login",Toast.LENGTH_SHORT).show()
        }

            binding.btnLogin.setOnClickListener {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

    }


    private fun gotToProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
        finish()
    }
}