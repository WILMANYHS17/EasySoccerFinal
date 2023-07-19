package com.example.easysoccer1.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.databinding.ActivityMainBinding
import com.example.easysoccer1.ui.viewmodel.JoinSessionViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class JoinSessionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        screenSplash.setKeepOnScreenCondition { false }

        binding.TextViewCreateUser.setOnClickListener { onClickRegisterUser() }
        binding.TextViewCreateAdmin.setOnClickListener { onClickRegisterAdmin() }
        binding.buttonLogin.setOnClickListener {
            if (validationRegister()) {
                lifecycleScope.launch {
                    onClickHomeAdmin()
                }

            }
        }
        binding.textViewForgotPassword.setOnClickListener { onClickForgotPassword() }
    }

    private fun onClickRegisterUser() {
        val intent = Intent(this, RegisterUserActivity::class.java)
        intent.putExtra("user", "User")
        startActivity(intent)
    }

    private fun onClickRegisterAdmin() {
        val intent = Intent(this, RegisterUserActivity::class.java)
        intent.putExtra("user", "Admin")
        startActivity(intent)
    }

    private fun onClickForgotPassword() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private suspend fun onClickHomeAdmin() {
        val joinSessionViewModel: JoinSessionViewModel by viewModel()
        Log.i("Entra", "Sí")
        val email = binding.editTextEmail.text.toString()

        val joinSuccessful = joinSessionViewModel.searchUsers1(email).getOrNull()

        joinSuccessful?.let { user ->
            if (validateLogin(user.email, user.password)) {
                val editor = getSharedPreferences("easySoccer", MODE_PRIVATE).edit()
                editor.putString("email", user.email)
                editor.apply()
                if (user.isAdmin) {
                    Log.i("Actividad Admin", "Inició")
                    val intent = Intent(this, SelectSportCenterActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.i("Actividad User", "Inició")
                    val intent = Intent(this, NavigationUserActivity::class.java)
                    startActivity(intent)
                }

            } else {
                binding.editTextEmail.setError("Email o contraseña incorrecta")
            }
        } ?: binding.editTextEmail.setError("Email o contraseña incorrecta")

    }

    private fun validateLogin(email: String, password: String): Boolean {
        return binding.editTextEmail.text.toString() == email
                && binding.editTextPassword.text.toString() == password

    }

    fun validationRegister(): Boolean {
        if (binding.editTextEmail.text?.isEmpty() == true && binding.editTextPassword.text?.isEmpty() == true) {
            binding.editTextEmail.setError("El espacio está vacio")
            binding.editTextPassword.setError("El espacio está vacio")
            return false
        } else {
            binding.editTextEmail.setError(null)
            binding.editTextPassword.setError(null)
            return true
        }
    }
}







