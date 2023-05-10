package com.example.easysoccer1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.databinding.ActivitySelectSportCenterBinding
import com.example.easysoccer1.ui.adapter.SelectSportCenterAdapter

class SelectSportCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectSportCenterBinding
    private val selectSportCenterAdapter by lazy {
        SelectSportCenterAdapter(
            ::selectSportCenter
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        HeaderProfileUser(binding!!.headerUser,this).build()
        Log.i("SportCenter", "Start")

        setUpAdapter()
        selectSportCenterAdapter.setListSelectSportCenter(getListSelectSportCenter())
        binding.buttonSelectCreateSportCenter.setOnClickListener { getRegisterSportCenter() }
    }

    fun getListSelectSportCenter(): List<SportCenter>{

        return arrayListOf(

        )
    }

    fun getRegisterSportCenter(){

        val intent = Intent(this, RegisterSportCenterActivity::class.java)
        startActivity(intent)
    }

    fun setUpAdapter(){
        binding.recyclerViewSelectSportCenter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter= selectSportCenterAdapter
        }
    }

    fun selectSportCenter(sportCenter: SportCenter){
        val intent = Intent(this, NavigationAdminActivity::class.java)
        startActivity(intent)
    }
}