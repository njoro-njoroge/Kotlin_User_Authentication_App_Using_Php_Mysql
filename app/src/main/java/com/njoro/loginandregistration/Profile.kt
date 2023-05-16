package com.njoro.loginandregistration

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.njoro.ecommerce.utils.SessionManager
import com.njoro.loginandregistration.databinding.ActivityProfileBinding

class Profile : AppCompatActivity() {

    private var binding: ActivityProfileBinding? = null
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        sessionManager = SessionManager(this)

        // get user details
        val userDetails = sessionManager.getUserDetails()

        binding!!.apply {
            txvName.text =
                "${userDetails[SessionManager.KEY_USER_FIRST_NAME]} ${userDetails[SessionManager.KEY_USER_LAST_NAME]}"
           txvUsername.text = userDetails[SessionManager.KEY_USER_NAME]
           txvPhoneNumber.text = userDetails[SessionManager.KEY_USER_PHONE_NUMBER]
           txvEmail.text = userDetails[SessionManager.KEY_USER_EMAIL]

            btnLogout.setOnClickListener {
                logoutAlert()
            }
        }
    }
    private fun logoutAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")
        builder.setNegativeButton("No", null)
        builder.setPositiveButton("OK") { dialog, _ ->
           logoutUser()
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun logoutUser(){
        sessionManager.logout()

        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()

        Toast.makeText(this,"You have logged out", Toast.LENGTH_LONG).show()
    }
}