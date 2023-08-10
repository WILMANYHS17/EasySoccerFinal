package com.example.easysoccer1.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.R
import com.example.easysoccer1.data.models.Comments
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.databinding.ActivityDescriptionSportCenterBinding
import com.example.easysoccer1.ui.adapter.CommentsUserAdapter
import com.example.easysoccer1.ui.viewmodel.DescriptionSportCenterViewModel
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DescriptionSportCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionSportCenterBinding
    private lateinit var nit: String
    private val commentsUserAdapter by lazy {
        CommentsUserAdapter(
            ::goComment
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_sport_center)
        binding = ActivityDescriptionSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        nit = intent.extras!!.getString("Nit1") ?: ""
        lifecycleScope.launch {
            commentsUserAdapter.setListComment(getListComment())
            val prefs = applicationContext.getSharedPreferences(
                "easySoccer",
                AppCompatActivity.MODE_PRIVATE
            )
            HeaderProfileUser(
                binding!!.headerUser,
                this@DescriptionSportCenterActivity,
                headerProfileUserViewModel,
                prefs
            ).build()
            getSportCenterUser(nit)
        }

        binding.buttonlocateSportCenter.setOnClickListener { onLocateSportCenter() }
        binding.buttonReserve.setOnClickListener { goToReserve() }
        binding.buttonCreateComment.setOnClickListener {
            lifecycleScope.launch {
                createComment()
            }
        }
    }

    private fun getListComment(): List<Users> {
        val descriptionSportCenterViewModel: DescriptionSportCenterViewModel by viewModel()

    }


    suspend fun createComment() {
        val prefs = applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailUser = prefs.getString("email", "")
        val descriptionSportCenterViewModel: DescriptionSportCenterViewModel by viewModel()
        val sportCenter = descriptionSportCenterViewModel.getSportCenterUser(nit)
        val user = emailUser?.let { descriptionSportCenterViewModel.getUser(it) }
        val idComment = generateRandomNumber()
        descriptionSportCenterViewModel.setComment(
            Comments(
                nameUser = user!!.getOrNull()!!.nameUser,
                comment = binding.textComment.text.toString(),
                nameSportCenter = sportCenter.getOrNull()!!.nameSportCenter,
                id = idComment.toString()
            )
        )

    }

    private fun goToReserve() {
        val intent = Intent(this, ReserveUserActivity::class.java)
        intent.putExtra("Nit", nit)
        startActivity(intent)
    }

    private fun onLocateSportCenter() {
        val intent = Intent(this, MapActivity::class.java)
        startActivity(intent)
    }

    suspend fun getSportCenterUser(nit: String?) {
        val descriptionSportCenterUser: DescriptionSportCenterViewModel by viewModel()
        val descriptionSportCenter = descriptionSportCenterUser.getSportCenterUser(nit)
        binding.titleSportCenter.text =
            descriptionSportCenter?.getOrNull()?.nameSportCenter.toString()
        binding.descriptionSportCenter.text =
            descriptionSportCenter?.getOrNull()?.description.toString()
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