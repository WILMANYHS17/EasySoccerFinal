package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.easysoccer1.R
import com.example.easysoccer1.data.models.RegisterUsers
import com.example.easysoccer1.databinding.ActivityRegisterUserBinding
import com.example.easysoccer1.ui.calendarUser.DatePickerAgeFragment
import com.example.easysoccer1.ui.viewmodel.RegisterUserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var typeUser: String


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val registerUserViewModel: RegisterUserViewModel by viewModel()


        typeUser = intent.extras!!.getString("user") ?: ""

        if (typeUser == "Admin") {
            binding.TittleRegister.text = getString(R.string.RegisterAdmin)
            binding.editTextId.visibility = View.VISIBLE
            binding.editTextIdLayout.visibility = View.VISIBLE

        } else {
            binding.TittleRegister.text = getString(R.string.RegisterUser)
        }


        binding.buttonRegister.setOnClickListener {
            if(validationRegister()){
                AlertDialog.Builder(this).apply {
                    setTitle("Registro de Usuario")
                    setMessage("¿Estás seguro de registrarte con este usuario? Más adelante lo puedes editar.")
                    setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                        registerUserViewModel.createUser(
                            RegisterUsers(
                                name = binding.editTextName.text.toString(),
                                phone = binding.editTextPhone.text.toString(),
                                email = binding.editTextEmailRegister.text.toString(),
                                nameUser = binding.editTextNameUser.text.toString(),
                                password = binding.editTextPassword.text.toString(),
                                birthday = binding.editTextDate.text.toString(),
                                isAdmin = typeUser
                            )
                        )
                    }
                    setNegativeButton("No", null)
                }.show()

            }


        }
        binding.editTextDate.setOnClickListener {
            showDatePickerDialog()
        }
        binding.backButton.setOnClickListener { onClickBackActivity() }

        binding.buttonRegisterCancel.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Cancelar Registro")
                setMessage("¿Está seguro de cancelar el registro de Usuario?")
                setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                    onClickBackActivity()
                }
                setNegativeButton("No", null)
            }.show()
        }


    }

    fun validationRegister(): Boolean {
        if (binding.editTextName.text?.isEmpty() == true && binding.editTextPhone.text?.isEmpty() == true &&
            binding.editTextEmailRegister.text?.isEmpty() == true && binding.editTextNameUser.text?.isEmpty() == true &&
            binding.editTextPassword.text?.isEmpty() == true && binding.editTextDate.text?.isEmpty() == true
        ) {
            binding.editTextName.setError("El espacio está vacio")
            binding.editTextPhone.setError("El espacio está vacio")
            binding.editTextEmailRegister.setError("El espacio está vacio")
            binding.editTextNameUser.setError("El espacio está vacio")
            binding.editTextPassword.setError("El espacio está vacio")
            binding.editTextDate.setError("El espacio está vacio")
            return false
        } else {

            binding.editTextName.setError(null)
            binding.editTextPhone.setError(null)
            binding.editTextEmailRegister.setError(null)
            binding.editTextNameUser.setError(null)
            binding.editTextPassword.setError(null)
            binding.editTextDate.setError(null)
            return true
        }
    }


    private fun onClickBackActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerAgeFragment { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.editTextDate.setText("$day / $month / $year")
    }


}