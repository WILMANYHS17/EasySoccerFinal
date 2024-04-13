package com.wyhs.easysoccer.ui.view

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.databinding.ActivityForgotPasswordBinding
import com.wyhs.easysoccer.ui.viewmodel.ForgotPasswordViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private val forgotPasswordViewModel: ForgotPasswordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()


        binding.backButton.setOnClickListener { onClickBackActivity() }
        binding.buttonCancelForgotPassword.setOnClickListener {
            onClickBackActivity()
        }
        binding.buttonRegister.setOnClickListener {
            lifecycleScope.launch {
                forgotPassword()
            }
        }
    }

    private suspend fun forgotPassword() {

        val emailUser = binding.editTextEmailRegister.text.toString()
        val password = binding.editTextPassword.text.toString()



        if (forgotPasswordViewModel.validationValue(emailUser, password)) {
            binding.editTextEmailRegister.error = "El espacio está vacio"
            binding.editTextPassword.error = "El espacio está vacio"
        } else {
            val forgotPassword = forgotPasswordViewModel.getUser(emailUser)
            if (forgotPasswordViewModel.validationEmail(forgotPassword)) {
                binding.editTextEmailRegister.error = "Error en el email o no existe"
            } else {
                AlertDialog.Builder(this).apply {
                    setTitle("Cambio de contraseña")
                    setMessage("¿Estás seguro de cambiar de contraseña?")
                    setPositiveButton("Sí") { _: DialogInterface, _: Int ->

                        forgotPasswordViewModel.changePassword(
                            Users(
                                email = emailUser,
                                password = password,
                                identification = forgotPassword.getOrNull()?.identification.toString(),
                                birthday = forgotPassword.getOrNull()?.birthday.toString(),
                                nameUser = forgotPassword.getOrNull()?.nameUser.toString(),
                                name = forgotPassword.getOrNull()?.name.toString(),
                                phone = forgotPassword.getOrNull()?.phone.toString(),
                                isAdmin = forgotPassword.getOrNull()?.isAdmin ?: false,
                            )
                        )
                    }
                    setNegativeButton("No", null)
                }.show()
            }

        }

    }

    private fun onClickBackActivity() {
        val intent = Intent(this, JoinSessionActivity::class.java)
        startActivity(intent)
    }

}