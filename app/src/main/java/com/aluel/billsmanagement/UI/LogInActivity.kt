package com.aluel.billsmanagement.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aluel.billsmanagement.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}