package com.aluel.billsmanagement.UI

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.aluel.billsmanagement.Model.LogInResponse
import com.aluel.billsmanagement.Model.LoginRequest
import com.aluel.billsmanagement.Utils.constants
import com.aluel.billsmanagement.ViewModel.LogInViewModel
import com.aluel.billsmanagement.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    val logInViewModel: LogInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()
        binding.btnLogIn.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
            validateLogIn()
        }

        logInViewModel.logLiveData.observe(this, Observer { LogInResponse ->
            binding.progressBar2.visibility = View.GONE
            persistLogin(LogInResponse)
            Toast.makeText(this, LogInResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this,Home::class.java))
        })
        logInViewModel.errorsLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.progressBar2.visibility = View.GONE
        })
    }
    fun validateLogIn() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        var error = false


        if (email.isBlank()) {
            binding.tilEmail.error = "email is needed"
            error = true

        }
        if (password.isBlank()) {
            binding.tilPassword.error = "email is needed"
            error = true
        }
        if (!error) {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            binding.progressBar2.visibility = View.VISIBLE
            logInViewModel.logInUser(loginRequest)
        }
    }
    fun persistLogin(logInResponse: LogInResponse){
        val sharedPrefs=getSharedPreferences(constants.PREFS, Context.MODE_PRIVATE)
        val editor=sharedPrefs.edit()
         editor.putString(constants.USER_ID,logInResponse.userId.toString())
           editor.putString(constants.ACCESS_TOKEN,logInResponse.accessToken)
            editor.apply()
        }
    }



