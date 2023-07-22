package com.example.easysoccer1.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.R
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.databinding.ActivityReserveUserBinding
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import com.example.easysoccer1.ui.viewmodel.ReserveUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReserveUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityReserveUserBinding
    lateinit var nit: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_user)
        binding = ActivityReserveUserBinding.inflate(layoutInflater)
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
                this@ReserveUserActivity,
                headerProfileUserViewModel,
                prefs
            ).build()
        }

        nit = intent.extras!!.getString("Nit") ?: ""
        lifecycleScope.launch {
            loadDataReserve()
        }

        binding.buttonReserve.setOnClickListener { generateRandomNumber() }

    }

    suspend fun loadDataReserve() {
        val reserveUserViewModel: ReserveUserViewModel by viewModel()
        val sportCenter = reserveUserViewModel.getSportCenter(nit)
        binding.nameSportCenterReservation.text = sportCenter.getOrNull()?.nameSportCenter
        binding.adressSportCenterReservation.text = sportCenter.getOrNull()?.address
        binding.reservationPrice.text = sportCenter.getOrNull()?.price5vs5
    }


    fun generateRandomNumber(): Int {
        val generatedNumbers = mutableSetOf<Int>()
        var number: Int
        do {
            number = (100000..999999).random()
        } while (generatedNumbers.contains(number))
        generatedNumbers.add(number)
        createReserve(number)
        return number
    }

    private fun createReserve(number: Int) {
        val reserveUserViewModel: ReserveUserViewModel by viewModel()
        val prefs = applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val properName = prefs.getString("Name", "")
        val emailUser = prefs.getString("email", "")

        reserveUserViewModel.setReserve(
            Reserve(
                nameSportCenter = binding.nameSportCenterReservation.text.toString(),
                address = binding.adressSportCenterReservation.text.toString(),
                numberPlayers = binding.numberPlayersReservation.text.toString(),
                date = binding.reservationDate.text.toString(),
                hour = binding.reservationHour.text.toString(),
                price = binding.reservationPrice.text.toString(),
                nameReserveBy = properName.toString(),
                numberReserve = number.toString()
            ), emailUser
        )
    }
}