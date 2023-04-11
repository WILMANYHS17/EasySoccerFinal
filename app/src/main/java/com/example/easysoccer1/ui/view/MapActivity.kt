package com.example.easysoccer1.ui.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.easysoccer1.R
import com.example.easysoccer1.databinding.ActivityMapBinding
import com.example.easysoccer1.data.models.RouteResponse
import com.example.easysoccer1.ui.interfaces.ApiService
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private lateinit var map: GoogleMap
    private var myLocation: String = ""
    private var start: String = ""
    private var end: String = ""

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)

        setContentView(binding.root)
        supportActionBar!!.hide()



        createFragment()


    }

    fun createFragment() {
        val mapFragment: SupportMapFragment =
            supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        sendRoute()
        createMarker()
        enableLocation()
    }

    fun createMarker() {

        val coordinates = LatLng(5.5262644, -73.3587758)
        val marker = MarkerOptions().position(coordinates).title("Invitus Fútbol 5")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f),
            4000,
            null
        )

    }

    fun isLocationPermission() =

        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    fun enableLocation() {

        if (!::map.isInitialized) return
        if (isLocationPermission()) {
            map.isMyLocationEnabled = true

        } else {
            requestLocationPermission()
        }
    }

    fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "Acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Acepta los permisos para encontrar la localización actual",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isLocationPermission()) {

            map.isMyLocationEnabled = false
            Toast.makeText(
                this,
                "Acepta los permisos para encontrar la localización actual",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    fun sendRoute() {
        getLocationCoordinates(this)

        if (start.isEmpty()) {
            start = myLocation
            end = "-73.3587758,5.5262644"
            createRoute()
        }
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
            myLocation = "${longitute},${latitute}"
            return Pair(
                location.latitude,
                location.longitude
            ) // Devuelve un par con las coordenadas
        } else {
            return null // Si no se puede obtener la ubicación, devuelve null
        }
    }

    fun createRoute() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiService::class.java)
                .getRoute("5b3ce3597851110001cf62481f51957ab1d149de8fd09afeac3c6080", start, end)
            if (call.isSuccessful) {
                Log.i("aris", "OK")
                drawRoute(call.body())

            } else {
                Log.i("aris", "KO")
            }
        }

    }

    fun drawRoute(routeResponse: RouteResponse?) {
        val polyLineOptions = PolylineOptions()
        routeResponse?.features?.first()?.geometry?.coordinates?.forEach {
            polyLineOptions.add(LatLng(it[1], it[0]))
        }
        runOnUiThread {
            val poly = map.addPolyline(polyLineOptions)
        }

    }

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.openrouteservice.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()

    }
}