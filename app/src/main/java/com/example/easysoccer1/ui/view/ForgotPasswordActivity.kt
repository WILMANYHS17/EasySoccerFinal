package com.example.easysoccer1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easysoccer1.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()


        binding.backButton.setOnClickListener { onClickBackActivity() }
        binding.buttonCancelForgotPassword.setOnClickListener {
            if (validationRegister()) {
                onClickBackActivity()
            }
        }
    }

    private fun onClickBackActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun validationRegister(): Boolean {
        if (binding.editTextEmailRegister.text?.isEmpty() == true && binding.editTextPassword.text?.isEmpty() == true) {
            binding.editTextEmailRegister.setError("El espacio está vacio")
            binding.editTextPassword.setError("El espacio está vacio")
            return false
        } else {
            binding.editTextEmailRegister.setError(null)
            binding.editTextPassword.setError(null)
            return true
        }
    }
}