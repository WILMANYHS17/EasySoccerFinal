package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.R
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.databinding.ActivityRegisterUserBinding
import com.example.easysoccer1.ui.calendarUser.DatePickerAgeFragment
import com.example.easysoccer1.ui.viewmodel.RegisterUserViewModel
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterUserBinding
    private lateinit var typeUser: String
    private lateinit var uriImageUser: Uri
    private lateinit var editUser: String
    private lateinit var emailUser: String
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    var isAdmin = false
    private val registerUserViewModel: RegisterUserViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        pickMedia = registerForActivityResult(PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.imageCircle.setImageURI(uri)
                uriImageUser = uri
            }
        }
        typeUser = intent.extras!!.getString("user") ?: ""
        if (typeUser == "Admin") {
            isAdmin = true
            binding.TittleRegister.text = getString(R.string.RegisterAdmin)
            binding.editTextId.visibility = View.VISIBLE
            binding.editTextIdLayout.visibility = View.VISIBLE
        } else {
            binding.TittleRegister.text = getString(R.string.RegisterUser)
        }
        lifecycleScope.launch {
            editUser = intent.extras!!.getString("EditUser") ?: ""
            if (editUser == "Yes") {
                emailUser = intent.extras!!.getString("EmailUser") ?: ""
                getUser()
            }
        }

        binding.buttonRegister.setOnClickListener {
            lifecycleScope.launch {
                registerUser()
            }
        }


        binding.editTextDate.setOnClickListener {
            showDatePickerDialog()
        }
        binding.imageCircle.setOnClickListener { imageUser() }


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

    private suspend fun getUser() {
        val user = registerUserViewModel.getUser(emailUser)
        user?.let {
            binding.editTextName.setText(user.getOrNull()?.name)
            binding.editTextEmailRegister.setText(user.getOrNull()?.email)
            binding.editTextNameUser.setText(user.getOrNull()?.nameUser)
            binding.editTextPassword.setText(user.getOrNull()?.password)
            binding.editTextPhone.setText(user.getOrNull()?.phone)
            binding.editTextDate.text = user.getOrNull()?.birthday
            binding.editTextId.setText(user.getOrNull()?.identification)
        }
    }

    suspend fun registerUser() {
        val emailUsers = binding.editTextEmailRegister.text.toString()
        if (validationRegister()) {
            val validation = registerUserViewModel.getUser(emailUsers)
            if (validation?.isSuccess == true) {
                binding.editTextEmailRegister.setError("Ya existe ese email")
            }
            val emailPattern = "[a-zA-Z0-9._-]+@[gmail|hotmail]+\\.[a-z]+".toRegex()
            if (binding.editTextEmailRegister.text.toString().matches(emailPattern)) {
                binding.editTextEmailRegister.setError("La dirección de correo electrónico no es válida")
            } else {
                var url = ""
                if (uriImageUser != null) {
                    registerUserViewModel.setImageUser(
                        uriImageUser,
                        emailUsers
                    )
                    url = registerUserViewModel.getImageUser(emailUsers).getOrNull().toString()

                    val users = Users(
                        name = binding.editTextName.text.toString(),
                        phone = binding.editTextPhone.text.toString(),
                        email = emailUsers,
                        nameUser = binding.editTextNameUser.text.toString(),
                        password = binding.editTextPassword.text.toString(),
                        birthday = binding.editTextDate.text.toString(),
                        isAdmin = isAdmin,
                        identification = binding.editTextId.text.toString(),
                        imageUserUrl = url
                    )

                    AlertDialog.Builder(this@RegisterUserActivity).apply {
                        setTitle("Registro de Usuario")
                        setMessage("¿Estás seguro de registrarte con este usuario? Más adelante lo puedes editar.")
                        Log.i("Método", "Antes")
                        setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                            registerUserViewModel.createUser(users)
                        }
                        setNegativeButton("No", null)
                    }.show()
                } else {
                    AlertDialog.Builder(this@RegisterUserActivity).apply {
                        setTitle("Imagen de perfil")
                        setMessage("Por favor seleccione una imagen de perfil")
                        setPositiveButton("Aceptar", null)
                    }.show()
                }
            }
        }
    }

    fun validationRegister(): Boolean {
        var isValid = true
        if (binding.editTextName.text?.isEmpty() == true) {
            binding.editTextName.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.editTextName.setError(null)
        }

        if (binding.editTextPhone.text?.isEmpty() == true) {
            binding.editTextPhone.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.editTextPhone.setError(null)
        }

        if (binding.editTextEmailRegister.text?.isEmpty() == true) {
            binding.editTextEmailRegister.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.editTextEmailRegister.setError(null)
        }

        if (binding.editTextNameUser.text?.isEmpty() == true) {
            binding.editTextNameUser.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.editTextNameUser.setError(null)
        }

        if (binding.editTextPassword.text?.isEmpty() == true) {
            binding.editTextPassword.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.editTextPassword.setError(null)
        }

        if (binding.editTextDate.text?.isEmpty() == true) {
            binding.editTextDate.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.editTextDate.setError(null)
        }

        return isValid
    }


    private fun onClickBackActivity() {
        onBackPressed()
    }

    fun imageUser() {
        binding.imageCircle.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
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