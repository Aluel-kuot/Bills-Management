package com.aluel.billsmanagement.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aluel.billsmanagement.R
import com.aluel.billsmanagement.databinding.ActivityHomeBinding
import com.example.billsmanagement.ui.SettingsFragment

class Home : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }

    override fun onResume() {
        super.onResume()
        setUpBottomNav()
    }

    fun setUpBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.summary->{
                    supportFragmentManager.beginTransaction().replace(R.id.FCVHome,
                        SummaryFragment()
                    )
                        .commit()
                    true


                }
                R.id.upcoming->{
                    supportFragmentManager.beginTransaction().replace(R.id.FCVHome,
                        UpcomingBillsFragment()
                    )
                        .commit()
                    true

                }
                R.id.paid->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.FCVHome, PaidBillsFragment())
                        .commit()
                    true

                }
                R.id.settings->{
                    supportFragmentManager.beginTransaction().replace(R.id.FCVHome,
                        SettingsFragment()
                    )
                        .commit()
                    true

                }
                else->{
                    false

                }
            }


        }

    }


    }
