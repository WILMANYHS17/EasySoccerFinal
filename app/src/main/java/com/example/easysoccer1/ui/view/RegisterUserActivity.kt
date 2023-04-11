package com.example.easysoccer1.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.easysoccer1.R
import com.example.easysoccer1.data.models.RegisterUsers
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

        typeUser = intent.extras!!.getString("user") ?: ""
        binding.buttonRegister.setOnClickListener {
            registerUserViewModel.createUser(
                RegisterUsers(
                    name = binding.editTextName.text.toString(),
                    phone = binding.editTextPhone.text.toString(),
                    email = binding.editTextEmailRegister.text.toString(),
                    nameUser = binding.editTextNameUser.text.toString(),
                    password = binding.editTextContraseA.text.toString(),
                    birthday = binding.editTextDate.text.toString(),
                    isAdmin = typeUser
                )
            )
        }


        if (typeUser == "Admin") {
            binding.TittleRegister.text = getString(R.string.RegisterAdmin)
            binding.editTextId.visibility = View.VISIBLE

        } else {
            binding.TittleRegister.text = getString(R.string.RegisterUser)
        }
    }
}