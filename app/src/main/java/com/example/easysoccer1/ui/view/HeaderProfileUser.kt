package com.example.easysoccer1.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
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
        var isAdmin = false
        binding.backButton.setOnClickListener {
            (context as Activity).onBackPressed()
        }

        val email = prefs.getString("email", "")
        email?.let {
            binding.nameUserProfile.text = getNameUser(it).getOrNull()?.nameUser
            isAdmin = getNameUser(it).getOrNull()?.isAdmin == true
        }
        val url = email?.let { getImageUser(it) }
        Glide.with(context).load(url?.getOrNull().toString()).into(binding.imvUser)
        binding.imvUser.setOnClickListener {

            val intent = Intent(context, RegisterUserActivity::class.java)
            intent.putExtra("UserHeader", isAdmin)
            intent.putExtra("EditUser", "Yes")
            intent.putExtra("EmailUser", email)
            startActivity(context, intent, null)
        }
    }

    suspend fun getNameUser(email: String): Result<Users> {
        return headerProfileUserViewModel.getNameUser(email)
    }

    suspend fun getImageUser(email: String): Result<String?> {
        return headerProfileUserViewModel.getImageUser(email)
    }
}