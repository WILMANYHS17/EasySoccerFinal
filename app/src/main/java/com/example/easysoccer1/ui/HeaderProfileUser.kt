package com.example.easysoccer1.ui

import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.easysoccer1.ui.view.RegisterUserActivity
import com.example.easysoccer1.databinding.HeaderProfileBinding

class HeaderProfileUser (val binding: HeaderProfileBinding, val fragment: Fragment) {

    fun build() {
        binding.backButton.setOnClickListener {
            fragment.requireActivity().onBackPressed()
        }
        binding.nameUserProfile.text =
            "Wilman Yecid Hernández Suesca"

        binding.imageProfile.setOnClickListener {
            val intent = Intent(fragment.activity, RegisterUserActivity::class.java)
            intent.putExtra("user", "User")
            ContextCompat.startActivity(fragment.context!!, intent, null)
        }


    }
}