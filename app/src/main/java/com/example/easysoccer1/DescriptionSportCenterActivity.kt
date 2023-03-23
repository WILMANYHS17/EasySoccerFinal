package com.example.easysoccer1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easysoccer1.databinding.ActivityDescriptionSportCenterBinding
import com.example.easysoccer1.databinding.ActivityMainBinding

class DescriptionSportCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionSportCenterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_sport_center)
        binding = ActivityDescriptionSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        binding.buttonlocateSportCenter.setOnClickListener{onLocateSportCenter()}
    }

    private fun onLocateSportCenter() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }
}