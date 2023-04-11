package com.example.easysoccer1.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.easysoccer1.R
import com.example.easysoccer1.databinding.ActivityRegisterUserBinding
import com.example.easysoccer1.ui.viewmodel.RegisterUserViewModel

class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var typeUser: String

    private val registerUserViewModel: RegisterUserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        registerUserViewModel.registerUserModel.observe(this, Observer {
            binding.editTextName.text = it.name

        })




        typeUser = intent.extras!!.getString("user") ?: ""
        if (typeUser == "Admin") {
            binding.TittleRegister.text = getString(R.string.RegisterAdmin)
            binding.editTextId.visibility = View.VISIBLE

        } else {
            binding.TittleRegister.text = getString(R.string.RegisterUser)
        }
    }
}