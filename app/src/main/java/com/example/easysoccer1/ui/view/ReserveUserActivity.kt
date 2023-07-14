package com.example.easysoccer1.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.easysoccer1.R
import com.example.easysoccer1.databinding.ActivityReserveUserBinding

class ReserveUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityReserveUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve_user)
        binding = ActivityReserveUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}