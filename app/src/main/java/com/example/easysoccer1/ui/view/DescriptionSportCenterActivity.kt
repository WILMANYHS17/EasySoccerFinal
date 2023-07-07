package com.example.easysoccer1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easysoccer1.R
import com.example.easysoccer1.databinding.ActivityDescriptionSportCenterBinding
import com.example.easysoccer1.ui.viewmodel.DescriptionSportCenterViewModel
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
        nit = intent.extras!!.getString("Nit") ?: ""
        getSportCenterUser(nit)

        binding.buttonlocateSportCenter.setOnClickListener { onLocateSportCenter() }
    }

    private fun onLocateSportCenter() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    fun getSportCenterUser(nit: String?){
        val descriptionSportCenterUser: DescriptionSportCenterViewModel by viewModel()
        descriptionSportCenterUser.getSportCenterUser(nit)
    }
}