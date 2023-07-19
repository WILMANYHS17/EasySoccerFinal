package com.example.easysoccer1.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.startActivity
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.databinding.HeaderProfileBinding
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel

class HeaderProfileUser(
    val binding: HeaderProfileBinding,
    val context: Context,
    val headerProfileUserViewModel: HeaderProfileUserViewModel,
    val prefs: SharedPreferences
) {

    suspend fun build() {
        binding.backButton.setOnClickListener {
            (context as Activity).onBackPressed()
        }

        val email = prefs.getString("email", "")
        email?.let {
            binding.nameUserProfile.text =
                getNameUser(it).getOrNull()?.nameUser
        }

        binding.imvUser.setOnClickListener {
            val intent = Intent(context, RegisterUserActivity::class.java)
            intent.putExtra("user", "User")
            startActivity(context, intent, null)
        }
    }

    suspend fun getNameUser(email: String): Result<Users> {
        return headerProfileUserViewModel.getNameUser(email)
    }
}