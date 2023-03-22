package com.example.easysoccer1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.easysoccer1.databinding.ActivityRegisterUserBinding

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var typeUser: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        typeUser = intent.extras!!.getString("user") ?: ""
        if (typeUser == "Admin") {
            binding.TittleRegister.text = getString(R.string.RegisterAdmin)
            binding.editTextId.visibility = View.VISIBLE

        } else {
            binding.TittleRegister.text = getString(R.string.RegisterUser)
        }
    }
}