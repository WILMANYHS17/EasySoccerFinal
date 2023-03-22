package com.example.easysoccer1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.easysoccer1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        screenSplash.setKeepOnScreenCondition{false}
        binding.TextViewCreateUser.setOnClickListener { onClickRegisterUser() }
        binding.TextViewCreateAdmin.setOnClickListener{ onClickRegisterAdmin()}
        binding.buttonLogin.setOnClickListener{onClickHomeAdmin()}

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
    private fun onClickHomeAdmin(){
        if(binding.editTextEmail.text?.isNotEmpty() == true && binding.editTextPassword.text?.isNotEmpty() == true){
            val intent = Intent(this, NavigationAdminActivity::class.java)
            startActivity(intent)

        }else{
            val intent = Intent(this, NavigationUserActivity::class.java)
            startActivity(intent)

        }

    }


}