package com.wyhs.easysoccer.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.databinding.ActivitySelectSportCenterBinding
import com.wyhs.easysoccer.ui.adapter.SportCenterAdminAdapter
import com.wyhs.easysoccer.ui.viewmodel.HeaderProfileUserViewModel
import com.wyhs.easysoccer.ui.viewmodel.SelectSportCenterViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SelectSportCenterActivity : AppCompatActivity() {
    lateinit var binding: ActivitySelectSportCenterBinding

    private val sportCenterAdapter by lazy {
        SportCenterAdminAdapter(
            ::goToSportCenter
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        setUpAdapter()
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        lifecycleScope.launch {
            sportCenterAdapter.setListSelectSportCenter(getListSportCenter())
            val prefs = applicationContext.getSharedPreferences(
                "easySoccer",
                AppCompatActivity.MODE_PRIVATE
            )
            HeaderProfileUser(
                binding!!.headerUser,
                this@SelectSportCenterActivity,
                headerProfileUserViewModel,
                prefs
            ).build()
        }

        binding.buttonCreateSportCenter.setOnClickListener { goToCreateSportCenter() }

    }

    suspend fun getListSportCenter(): List<SportCenter> {
        val selectSportCenterViewModel: SelectSportCenterViewModel by viewModel()
        val prefs = getSharedPreferences("easySoccer", AppCompatActivity.MODE_PRIVATE)
        val emailAdmin = prefs.getString("email", "")
        return selectSportCenterViewModel.getListSportCenter(emailAdmin).getOrNull() ?: emptyList()
    }

    private fun goToCreateSportCenter() {
        val intent = Intent(this, RegisterSportCenterActivity::class.java)
        intent.putExtra("Edit", "No")
        startActivity(intent)
    }

    fun goToSportCenter(nit: String) {
        val intent = Intent(this, NavigationAdminActivity::class.java)
        intent.putExtra("Nit", nit)
        startActivity(intent)
    }

    fun setUpAdapter() {
        binding.recyclerSelectSportCenter.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sportCenterAdapter
        }
    }

}