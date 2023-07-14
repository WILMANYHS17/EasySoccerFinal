package com.example.easysoccer1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.R
import com.example.easysoccer1.databinding.ActivityDescriptionSportCenterBinding
import com.example.easysoccer1.ui.viewmodel.DescriptionSportCenterViewModel
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionSportCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionSportCenterBinding
    private lateinit var nit: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_sport_center)
        binding = ActivityDescriptionSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        nit = intent.extras!!.getString("Nit1") ?: ""
        lifecycleScope.launch {
            val prefs = applicationContext.getSharedPreferences(
                "easySoccer",
                AppCompatActivity.MODE_PRIVATE
            )
            HeaderProfileUser(binding!!.headerUser, this@DescriptionSportCenterActivity, headerProfileUserViewModel, prefs).build()
            getSportCenterUser(nit)
        }

        binding.buttonlocateSportCenter.setOnClickListener { onLocateSportCenter() }
    }

    private fun onLocateSportCenter() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    suspend fun getSportCenterUser(nit: String?) {
        val descriptionSportCenterUser: DescriptionSportCenterViewModel by viewModel()

        val descriptionSportCenter = descriptionSportCenterUser.getSportCenterUser(nit)
        binding.titleSportCenter.text = descriptionSportCenter?.getOrNull()?.nameSportCenter.toString()
        binding.descriptionSportCenter.text = descriptionSportCenter?.getOrNull()?.description.toString()
    }
}