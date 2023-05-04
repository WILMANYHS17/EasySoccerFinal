package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.databinding.ActivityRegisterSportCenterBinding
import com.example.easysoccer1.ui.viewmodel.RegisterSportCenterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterSportCenterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSportCenterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.buttonRegisterSportCenter.setOnClickListener { onClickCreateSportCenter() }
        binding.buttonRegisterSportCenterCancel.setOnClickListener { onClickBackActivity() }
        binding.backButton.setOnClickListener { onClickBackActivity() }
    }

    fun onClickCreateSportCenter() {
        val registerSportCenterViewModel: RegisterSportCenterViewModel by viewModel()
        if (validationRegister()) {
            AlertDialog.Builder(this).apply {
                setTitle("Registro de Centro deportivo")
                setMessage("¿Estás seguro de registrar este Centro Deportivo? Más adelante lo puedes editar.")
                setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                    val prefs = getSharedPreferences("easySoccer", MODE_PRIVATE)
                    val emailAdmin = prefs.getString("email", "")


                    registerSportCenterViewModel.setSportCenter(
                        SportCenter(
                            name = binding.nameSportCenter.text.toString(),
                            address = binding.adressSportCenter.text.toString(),
                            nit = binding.nitSportCenter.text.toString(),
                            price5vs5 = binding.price5vs5.text.toString(),
                            price8vs8 = binding.price8vs8.text.toString(),
                            description = binding.descriptionSportCenter.text.toString(),
                            emailAdmin = emailAdmin.toString()
                        )
                    )
                }
                setNegativeButton("No", null)
            }.show()
        }

    }

    fun validationRegister(): Boolean {
        if (binding.nameSportCenter.text?.isEmpty() == true && binding.adressSportCenter.text?.isEmpty() == true &&
            binding.nitSportCenter.text?.isEmpty() == true && binding.price5vs5.text?.isEmpty() == true &&
            binding.price8vs8.text?.isEmpty() == true && binding.descriptionSportCenter.text?.isEmpty() == true
        ) {
            binding.nameSportCenter.setError("El espacio está vacio")
            binding.adressSportCenter.setError("El espacio está vacio")
            binding.nitSportCenter.setError("El espacio está vacio")
            binding.price5vs5.setError("El espacio está vacio")
            binding.price8vs8.setError("El espacio está vacio")
            binding.descriptionSportCenter.setError("El espacio está vacio")
            return false
        } else {
            binding.nameSportCenter.setError(null)
            binding.adressSportCenter.setError(null)
            binding.nitSportCenter.setError(null)
            binding.price5vs5.setError(null)
            binding.price8vs8.setError(null)
            binding.descriptionSportCenter.setError(null)
            return true
        }
    }

    fun onClickBackActivity() {
        val intent = Intent(this, NavigationAdminActivity::class.java)
        startActivity(intent)
    }
}