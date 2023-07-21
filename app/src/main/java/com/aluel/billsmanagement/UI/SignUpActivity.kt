package com.aluel.billsmanagement.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.aluel.billsmanagement.Model.RegisterRequest
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
        binding.btnSignUp.setOnClickListener {
            validate()
        }

        userViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.progressBar.visibility = View.GONE
            startActivity(Intent(this, LogInActivity::class.java))
        })
        userViewModel.regLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this, regResponse.message, Toast.LENGTH_LONG).show()
        })
        binding.progressBar.visibility=View.GONE
    }

    fun validate() {
        val name = binding.etFirstName.text.toString()
        val names = binding.etSecondName.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        var error = false

        if (name.isBlank()) {
            binding.tilSecondName.error = "name is required"
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
        if (!password.equals(password)) {
            binding.tilPassword.error = "password is required"
            error = true
        }

        if (!error) {
            val registerRequest = RegisterRequest(

                firstName = name,
                secondName = names,
                email = email,
                password = password,
                phoneNumber = phoneNumber
            )
            binding.progressBar.visibility = View.VISIBLE

        }
    }

}



