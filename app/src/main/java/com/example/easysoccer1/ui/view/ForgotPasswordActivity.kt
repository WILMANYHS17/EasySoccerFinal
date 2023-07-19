package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.databinding.ActivityForgotPasswordBinding
import com.example.easysoccer1.ui.viewmodel.ForgotPasswordViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        binding.buttonRegister.setOnClickListener {
            lifecycleScope.launch {
                forgotPassword()
            }
        }
    }

    private suspend fun forgotPassword() {
        val forgotPasswordViewModel: ForgotPasswordViewModel by viewModel()
        val emailUser = binding.editTextEmailRegister.text.toString()
        val forgotPassword = forgotPasswordViewModel.getUser(emailUser)


        if (validationRegister()) {
            AlertDialog.Builder(this).apply {
                setTitle("Cambio de contraseña")
                setMessage("¿Estás seguro de cambiar de contraseña?")
                setPositiveButton("Sí") { _: DialogInterface, _: Int ->


                    forgotPassword.getOrNull()?.isAdmin?.let {
                        Users(
                            email = emailUser,
                            password = binding.editTextPassword.text.toString(),
                            identification = forgotPassword.getOrNull()?.identification.toString(),
                            birthday = forgotPassword.getOrNull()?.birthday.toString(),
                            nameUser = forgotPassword.getOrNull()?.nameUser.toString(),
                            name = forgotPassword.getOrNull()?.name.toString(),
                            phone = forgotPassword.getOrNull()?.phone.toString(),
                            isAdmin = it,
                        )
                    }?.let {
                        forgotPasswordViewModel.changePassword(
                            it
                        )
                    }

                }
                setNegativeButton("No", null)
            }.show()
        }

    }

    private fun onClickBackActivity() {
        val intent = Intent(this, JoinSessionActivity::class.java)
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