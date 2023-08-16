package com.aluel.billsmanagement.UI

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Constants
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.aluel.billsmanagement.Model.RegisterRequest
import com.aluel.billsmanagement.Utils.constants
import com.aluel.billsmanagement.ViewModel.UserViewModel
import com.aluel.billsmanagement.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        binding.tvLogIn.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }
        binding.btnSignUp.setOnClickListener {
            validate()
            clearErrors()

        }

        userViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
        })

        userViewModel.regLiveData.observe(this, Observer { regResponse ->
            binding.progressBar.visibility=View.GONE
            Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()
            startActivity(Intent(this, LogInActivity::class.java))

        })
    }
    fun validate() {
        val name = binding.etFirstName.text.toString()
        val names = binding.etLastName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val confirm = binding.etConfirmPassword.text.toString()

        var error = false

        if (name.isBlank()) {
            binding.tilLastName.error = "name is required"
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = "phoneNumber is required"
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmail.error = "email is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "password is required"
            error = true
        }
        if (confirm !=(password)) {
            binding.tilConfirmPassword.error = "password do not match"
            error = true
        }
        if (!error) {
            val registerRequest = RegisterRequest(

                firstName = name,
                lastName = names,
                email = email,
                password = password,
                phoneNumber = phoneNumber
            )
            binding.progressBar.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
    }
    private fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilFirstName.error = null
        binding.tilPhoneNumber.error = null
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        binding.tilConfirmPassword.error = null
    }
    fun redirectUser(){
        val sharedPrefs=getSharedPreferences(constants.PREFS, MODE_PRIVATE)
        val userId=sharedPrefs.getString(constants.USER_ID,constants.EMPTY_STRING)
        if(userId.isNullOrBlank()){
            startActivity(Intent(this,LogInActivity::class.java))
        }

    }
}

