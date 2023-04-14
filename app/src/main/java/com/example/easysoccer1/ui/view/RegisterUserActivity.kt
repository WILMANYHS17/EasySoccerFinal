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
        binding.buttonRegister.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setTitle("Registro de Usuario")
                setMessage("¿Estás seguro de restrarte con este usuario? Más adelante lo puedes editar.")
                setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                    registerUserViewModel.createUser(
                        RegisterUsers(
                            name = binding.editTextName.text.toString(),
                            phone = binding.editTextPhone.text.toString(),
                            email = binding.editTextEmailRegister.text.toString(),
                            nameUser = binding.editTextNameUser.text.toString(),
                            password = binding.editTextContraseA.text.toString(),
                            birthday = binding.textViewDate.text.toString(),
                            isAdmin = typeUser
                        )
                    )
                }
                setNegativeButton("No", null)
            }.show()

        }
        binding.textViewDate.setOnClickListener {
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
            }
        }



        if (typeUser == "Admin") {
            binding.TittleRegister.text = getString(R.string.RegisterAdmin)
            binding.editTextId.visibility = View.VISIBLE

        } else {
            binding.TittleRegister.text = getString(R.string.RegisterUser)
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
        binding.textViewDate.setText("$day / $month / $year")
    }


}