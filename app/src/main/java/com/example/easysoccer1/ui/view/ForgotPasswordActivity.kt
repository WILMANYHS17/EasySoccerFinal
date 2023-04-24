package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.databinding.ActivityForgotPasswordBinding
import com.example.easysoccer1.ui.viewmodel.ForgotPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        val forgotPasswordViewModel: ForgotPasswordViewModel by viewModel()
        binding.backButton.setOnClickListener { onClickBackActivity() }
        binding.buttonCancelForgotPassword.setOnClickListener {
            if (validationRegister()) {
                onClickBackActivity()
            }
        }
        binding.buttonRegister.setOnClickListener {
            if (validationRegister()) {
                AlertDialog.Builder(this).apply {
                    setTitle("Cambio de contraseña")
                    setMessage("¿Estás seguro de cambiar de contraseña?")
                    setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                        forgotPasswordViewModel.changePassword(
                            ForgotPassword(
                                email = binding.editTextEmailRegister.text.toString(),
                                password = binding.editTextPassword.text.toString()
                            )
                        )

                    }
                    setNegativeButton("No", null)
                }.show()
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