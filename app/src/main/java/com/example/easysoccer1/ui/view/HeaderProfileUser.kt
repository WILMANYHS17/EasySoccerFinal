package com.example.easysoccer1.ui.view

import android.app.Activity
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.example.easysoccer1.databinding.HeaderProfileBinding

class HeaderProfileUser(val binding: HeaderProfileBinding, val fragment: Fragment?) {

    private var activity: Activity? = null

    constructor(binding: HeaderProfileBinding, activity: Activity) : this(
        binding,
        fragment = null as? Fragment
    ) {
        this.activity = activity
    }

    fun build() {
        binding.backButton.setOnClickListener {
            activity?.finish() ?: fragment?.activity?.finish()
        }
        binding.nameUserProfile.text =
            "Wilman Yecid Hernández Suesca"

        binding.imvUser.setOnClickListener {
            val intent = Intent(fragment?.activity, RegisterUserActivity::class.java)
            intent.putExtra("user", "User")
            fragment?.requireContext()?.let { it1 -> startActivity(it1, intent, null) }
        }


    }
}