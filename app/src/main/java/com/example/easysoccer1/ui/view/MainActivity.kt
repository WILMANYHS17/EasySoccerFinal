package com.example.easysoccer1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.databinding.ActivityMainBinding
import com.example.easysoccer1.ui.viewmodel.JoinSessionViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var joinSussesful = Result.success(false)
    private var isAdmin = Result.success(false)


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

    private fun onClickForgotPassword(){
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }

    private suspend fun onClickHomeAdmin() {
        val joinSessionViewModel: JoinSessionViewModel by viewModel()
        joinSussesful = joinSessionViewModel.searchUser(
            JoinSessionUsers(
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString(),
                isAdmin = true
            )
        )
        isAdmin = joinSessionViewModel.isAdmin(
            JoinSessionUsers(
                email = binding.editTextEmail.text.toString(),
                password = binding.editTextPassword.text.toString(),
                isAdmin = true
            )
        )
        if (joinSussesful.getOrDefault(false)) {
            if (isAdmin.getOrDefault(false)) {
                Log.i("Actividad Admin", "Inició")
                val intent = Intent(this, NavigationAdminActivity::class.java)
                startActivity(intent)
            } else {
                Log.i("Actividad User", "Inició")
                val intent = Intent(this, NavigationUserActivity::class.java)
                startActivity(intent)
            }
        } else {
            binding.editTextEmail.setError("Email o contraseña incorrecta")
        }
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





