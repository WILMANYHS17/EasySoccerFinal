package com.example.easysoccer1.ui.view

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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
    private lateinit var pickMediaImageMultiple: ActivityResultLauncher<Intent>
    private val uriList = mutableListOf<Uri>()
    private lateinit var locationSportCenter: String

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

        pickMediaImageMultiple =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val clipData = result.data?.clipData
                    if (clipData != null) {
                        for (i in 0 until clipData.itemCount) {
                            val uri = clipData.getItemAt(i).uri
                            uriList.add(uri)
                        }
                    } else {
                        val uri = result.data?.data
                        if (uri != null) {
                            uriList.add(uri)
                        }
                    }
                    Log.d("Lista", uriList.toString())
                }
            }


        editYes = intent.extras!!.getString("Edit") ?: ""
        if (editYes == "No") {
            binding.nitSportCenter.visibility = View.VISIBLE
            binding.editTextNitSportCenterLayout.visibility = View.VISIBLE
        }
        binding.imageSportCenter.setOnClickListener { imageSportCenter() }
        binding.buttonListImagesSportCenter.setOnClickListener { listImages() }
        binding.buttonRegisterSportCenter.setOnClickListener {
            lifecycleScope.launch {
                onClickCreateSportCenter()
            }
        }
        binding.buttonRegisterSportCenterCancel.setOnClickListener { onClickBackActivity() }

    }

    suspend fun onClickCreateSportCenter() {
        val registerSportCenterViewModel: RegisterSportCenterViewModel by viewModel()
        if (validationRegister()) {
            getLocationCoordinates(this)
            if (binding.nitSportCenter.text.isEmpty()) {
                nit = intent.extras!!.getString("Nit") ?: ""
            } else {
                nit = binding.nitSportCenter.text.toString()
            }
            val prefs = getSharedPreferences("easySoccer", MODE_PRIVATE)
            val emailAdmin = prefs.getString("email", "")
            val validation = registerSportCenterViewModel.getNitSportCenter(nit)
            if (validation?.isSuccess == true) {
                binding.nitSportCenter.setError("Ya existe este NIT")
            }else{
                registerSportCenterViewModel.setImageSportCenter(
                    nit,
                    uriImageSportCenter
                )
                registerSportCenterViewModel.setListImageSportCenter(uriList, nit)
                var url = ""
                url = registerSportCenterViewModel.getImageSportCenter(nit).getOrNull().toString()
                val sportCenter = SportCenter(
                    nameSportCenter = binding.nameSportCenter.text.toString(),
                    address = binding.addressSportCenter.text.toString(),
                    nit = nit,
                    price5vs5 = binding.price5vs5.text.toString(),
                    price8vs8 = binding.price8vs8.text.toString(),
                    description = binding.descriptionSportCenter.text.toString(),
                    emailAdmin = emailAdmin.toString(),
                    imageSportCenterUrl = url,
                    locationSportCenter = locationSportCenter
                )
                AlertDialog.Builder(this).apply {
                    setTitle("Registro de Centro deportivo")
                    setMessage("¿Estás seguro de registrar este Centro Deportivo? Más adelante lo puedes editar.")
                    setPositiveButton("Sí") { _: DialogInterface, _: Int ->
                        registerSportCenterViewModel.setSportCenter(sportCenter)
                    }
                    setNegativeButton("No", null)
                }.show()
            }
        }

    }

    fun validationRegister(): Boolean {
        var isValid = true
        if (binding.nameSportCenter.text?.isEmpty() == true) {
            binding.nameSportCenter.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.nameSportCenter.setError(null)
        }

        if (binding.nitSportCenter.text?.isEmpty() == true) {
            binding.nitSportCenter.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.nitSportCenter.setError(null)
        }

        if (binding.price5vs5.text?.isEmpty() == true) {
            binding.price5vs5.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.price5vs5.setError(null)
        }

        if (binding.price8vs8.text?.isEmpty() == true) {
            binding.price8vs8.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.price8vs8.setError(null)
        }

        if (binding.descriptionSportCenter.text?.isEmpty() == true) {
            binding.descriptionSportCenter.setError("El espacio está vacio")
            isValid = false
        } else {
            binding.descriptionSportCenter.setError(null)
        }
        return isValid
    }

    fun imageSportCenter() {
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    fun getLocationCoordinates(context: Context): Pair<Double, Double>? {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return null // Si no se han concedido los permisos necesarios, devuelve null
        }
        val location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
        if (location != null) {
            val latitute = location.latitude
            val longitute = location.longitude
            locationSportCenter = "${longitute},${latitute}"
            return Pair(
                location.latitude,
                location.longitude
            ) // Devuelve un par con las coordenadas
        } else {
            return null // Si no se puede obtener la ubicación, devuelve null
        }
    }

    fun listImages() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        pickMediaImageMultiple.launch(intent)
    }

    fun onClickBackActivity() {
        super.onBackPressed()
    }
}