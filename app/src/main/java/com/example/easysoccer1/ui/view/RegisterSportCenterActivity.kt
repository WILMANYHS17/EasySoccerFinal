package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.databinding.ActivityRegisterSportCenterBinding
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import com.example.easysoccer1.ui.viewmodel.RegisterSportCenterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegisterSportCenterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSportCenterBinding
    private lateinit var editYes: String
    private lateinit var nit: String
    private lateinit var uriImageSportCenter: Uri
    private lateinit var pickMedia: ActivityResultLauncher<PickVisualMediaRequest>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        lifecycleScope.launch {
            val prefs = applicationContext.getSharedPreferences(
                "easySoccer",
                AppCompatActivity.MODE_PRIVATE
            )
            HeaderProfileUser(
                binding!!.headerUser,
                this@RegisterSportCenterActivity,
                headerProfileUserViewModel,
                prefs
            ).build()
        }
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.imageSportCenter.setImageURI(uri)
                uriImageSportCenter = uri
            }
        }
        editYes = intent.extras!!.getString("Edit") ?: ""
        if (editYes == "No") {
            binding.nitSportCenter.visibility = View.VISIBLE
        }
        binding.imageSportCenter.setOnClickListener { imageSportCenter() }
        binding.buttonRegisterSportCenter.setOnClickListener { onClickCreateSportCenter() }
        binding.buttonRegisterSportCenterCancel.setOnClickListener { onClickBackActivity() }

    }

    fun onClickCreateSportCenter() {
        val registerSportCenterViewModel: RegisterSportCenterViewModel by viewModel()
        var url: String = ""
        if (validationRegister()) {
            AlertDialog.Builder(this).apply {
                setTitle("Registro de Centro deportivo")
                setMessage("¿Estás seguro de registrar este Centro Deportivo? Más adelante lo puedes editar.")
                setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                    val prefs = getSharedPreferences("easySoccer", MODE_PRIVATE)
                    val emailAdmin = prefs.getString("email", "")

                    if (binding.nitSportCenter.text.isEmpty()) {
                        nit = intent.extras!!.getString("Nit") ?: ""
                    } else {
                        nit = binding.nitSportCenter.text.toString()
                    }
                    registerSportCenterViewModel.setImageSportCenter(nit, uriImageSportCenter)
                    lifecycleScope.launch {
                        url = registerSportCenterViewModel.getImageSportCenter(nit).getOrNull().toString()
                    }

                    val sportCenter = SportCenter(
                        nameSportCenter = binding.nameSportCenter.text.toString(),
                        address = binding.addressSportCenter.text.toString(),
                        nit = nit,
                        price5vs5 = binding.price5vs5.text.toString(),
                        price8vs8 = binding.price8vs8.text.toString(),
                        description = binding.descriptionSportCenter.text.toString(),
                        emailAdmin = emailAdmin.toString(),
                        imageSportCenterUrl = url
                    )
                    //sportCenter.imagesSportCenterList.add("")
                    registerSportCenterViewModel.setSportCenter(
                        sportCenter
                    )

                }
                setNegativeButton("No", null)
            }.show()
        }

    }

    fun validationRegister(): Boolean {
        if (binding.nameSportCenter.text?.isEmpty() == true && binding.addressSportCenter.text?.isEmpty() == true &&
            binding.price5vs5.text?.isEmpty() == true &&
            binding.price8vs8.text?.isEmpty() == true && binding.descriptionSportCenter.text?.isEmpty() == true
        ) {
            binding.nameSportCenter.setError("El espacio está vacio")
            binding.addressSportCenter.setError("El espacio está vacio")
            binding.price5vs5.setError("El espacio está vacio")
            binding.price8vs8.setError("El espacio está vacio")
            binding.descriptionSportCenter.setError("El espacio está vacio")
            return false
        } else {
            binding.nameSportCenter.setError(null)
            binding.addressSportCenter.setError(null)
            binding.price5vs5.setError(null)
            binding.price8vs8.setError(null)
            binding.descriptionSportCenter.setError(null)
            return true
        }
    }

    fun imageSportCenter() {
        binding.imageSportCenter.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    fun onClickBackActivity() {
        super.onBackPressed()
    }
}