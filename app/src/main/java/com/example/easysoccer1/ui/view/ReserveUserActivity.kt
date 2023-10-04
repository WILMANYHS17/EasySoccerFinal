package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.R
import com.example.easysoccer1.data.models.Goals
import com.example.easysoccer1.data.models.Reserve
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
        binding.buttonReserve.setOnClickListener {
            lifecycleScope.launch {
                createReserve()
            }
        }

    }

    suspend fun loadDataReserve() {
        val reserveUserViewModel: ReserveUserViewModel by viewModel()
        val prefs = applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val date = prefs.getString("Date", "")
        val hour = prefs.getString("Hour", "")
        val size = prefs.getString("Size", "")
        val sportCenter = reserveUserViewModel.getSportCenter(nit)
        if (size == "5vs5") {
            binding.reservationPrice.text = sportCenter.getOrNull()?.price5vs5.toString()
        } else {
            binding.reservationPrice.text = sportCenter.getOrNull()?.price8vs8.toString()
        }
        binding.nameSportCenterReservation.text = sportCenter.getOrNull()?.nameSportCenter
        binding.adressSportCenterReservation.text = sportCenter.getOrNull()?.address
        binding.numberPlayersReservation.text = size
        binding.reservationDate.text = date
        binding.reservationHour.text = hour
    }

    private suspend fun createReserve() {
        val reserveUserViewModel: ReserveUserViewModel by viewModel()
        val size = binding.numberPlayersReservation.text.toString()
        val goal = reserveUserViewModel.getGoal(nit, size)
        val number = generateRandomNumber()
        val prefs = applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val properName = prefs.getString("Name", "")
        val emailUser = prefs.getString("email", "")
        AlertDialog.Builder(this).apply {
            setTitle("Reservando la cancha")
            setMessage("Está seguro de reservar la cancha?")
            setPositiveButton("Si") { _: DialogInterface, _: Int ->
                goal.getOrNull()?.let {
                    Reserve(
                        nameSportCenter = binding.nameSportCenterReservation.text.toString(),
                        address = binding.adressSportCenterReservation.text.toString(),
                        numberPlayers = binding.numberPlayersReservation.text.toString(),
                        date = binding.reservationDate.text.toString(),
                        hour = binding.reservationHour.text.toString(),
                        price = binding.reservationPrice.text.toString(),
                        nameReserveBy = properName.toString(),
                        numberReserve = number.toString(),
                        numberGoal = it.number
                    )
                }?.let {
                    reserveUserViewModel.setReserve(
                        it, emailUser
                    )
                }
                lifecycleScope.launch {
                    goal.getOrNull()?.let {
                        reserveUserViewModel.updateGoal(
                            Goals(
                                number = it.number,
                                size = binding.numberPlayersReservation.text.toString(),
                                price = binding.reservationPrice.text.toString(),
                                available = "Reservado",
                                hour = binding.reservationHour.text.toString(),
                                date = binding.reservationDate.text.toString()
                            ), it.number, nit
                        )
                    }
                }

            }
            setNegativeButton("No", null)
        }.show()

    }

    fun generateRandomNumber(): Int {
        val generatedNumbers = mutableSetOf<Int>()
        var number: Int
        do {
            number = (100000..999999).random()
        } while (generatedNumbers.contains(number))
        generatedNumbers.add(number)
        return number
    }
}