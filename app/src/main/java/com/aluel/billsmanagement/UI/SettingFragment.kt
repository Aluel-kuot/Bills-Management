package com.example.billsmanagement.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aluel.billsmanagement.R
import com.aluel.billsmanagement.UI.LogInActivity
import com.aluel.billsmanagement.Utils.constants

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)
        val logoutButton = view.findViewById<Button>(R.id.btnLogOut)

        logoutButton.setOnClickListener {

            performLogout()
        }

        return view
    }

    private fun performLogout() {
//        val sharedPrefs = requireActivity().getSharedPreferences(Constants.P Context.MODE_PRIVATE)
        val sharedPrefs=requireActivity().getSharedPreferences(constants.PREFS, Context.MODE_PRIVATE)

        val editor = sharedPrefs.edit()
        editor.clear().apply()

        val intent = Intent(requireContext(), LogInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        requireActivity().finish()
        Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

    }
}