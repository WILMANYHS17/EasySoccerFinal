package com.wyhs.easysoccer.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.wyhs.easysoccer.R
import com.wyhs.easysoccer.data.models.Comments
import com.wyhs.easysoccer.databinding.ActivityDescriptionSportCenterBinding
import com.wyhs.easysoccer.ui.adapter.CommentsUserAdapter
import com.wyhs.easysoccer.ui.adapter.ImagesDetailAdapter
import com.wyhs.easysoccer.ui.viewmodel.DescriptionSportCenterViewModel
import com.wyhs.easysoccer.ui.viewmodel.HeaderProfileUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.abs

class DescriptionSportCenterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionSportCenterBinding
    private lateinit var nit: String
    private val commentsUserAdapter by lazy {
        CommentsUserAdapter(
            ::goComment
        )
    }

    private val imagesDetailAdapter by lazy {
        ImagesDetailAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description_sport_center)
        binding = ActivityDescriptionSportCenterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        nit = intent.extras!!.getString("Nit1") ?: ""
        setUpAdapter()
        lifecycleScope.launch {
            //commentsUserAdapter.setListComment(getListComment())
            getSportCenterUser(nit)
            imagesDetailAdapter.setListImage(getListImageSportCenter())
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

        }
        binding.buttonlocateSportCenter.setOnClickListener {
            lifecycleScope.launch {
                onLocateSportCenter()
            }
        }

        binding.buttonReserve.setOnClickListener { goToReserve() }
        binding.buttonCreateComment.setOnClickListener {
            lifecycleScope.launch {
                createComment()
            }
        }
    }

    //private fun getListComment(): List<Comments> {
    // val descriptionSportCenterViewModel: DescriptionSportCenterViewModel by viewModel()
    //return

    //}


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
                emailUser = user!!.getOrNull()!!.email,
                nameUser = user.getOrNull()!!.nameUser,
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

    fun goComment(comments: Comments) {

    }

    private suspend fun onLocateSportCenter() {
        val descriptionSportCenterUser: DescriptionSportCenterViewModel by viewModel()
        val descriptionSportCenter = descriptionSportCenterUser.getSportCenterUser(nit)
        val intent = Intent(this, MapActivity::class.java)
        intent.putExtra("Coordinates", descriptionSportCenter.getOrNull()?.locationSportCenter)
        intent.putExtra("SportCenter", descriptionSportCenter.getOrNull()?.nameSportCenter)
        startActivity(intent)
    }

    suspend fun getListImageSportCenter(): List<String> {
        val descriptionSportCenterUser: DescriptionSportCenterViewModel by viewModel()
        return descriptionSportCenterUser.getListImageSportCenter(nit).getOrNull() ?: emptyList()
    }

    suspend fun getSportCenterUser(nit: String?) {
        val descriptionSportCenterUser: DescriptionSportCenterViewModel by viewModel()
        val descriptionSportCenter = descriptionSportCenterUser.getSportCenterUser(nit)
        binding.titleSportCenter.text =
            descriptionSportCenter?.getOrNull()?.nameSportCenter.toString()
        binding.descriptionSportCenter.text =
            descriptionSportCenter?.getOrNull()?.description.toString()
    }

    fun setUpAdapter() {
        binding.viewPageSportCenterUser.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            adapter = imagesDetailAdapter
            val compositePageTransformer = CompositePageTransformer()
            val pageTransformer = ViewPager2.PageTransformer { page, position ->
                var r = 1 - abs(position)
                page.scaleY = 0.85f + r * 0.15f
            }
            with(compositePageTransformer) {
                addTransformer(MarginPageTransformer(40))
                addTransformer(pageTransformer)
            }
            setPageTransformer(compositePageTransformer)
        }
        binding.recyclerViewComments.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commentsUserAdapter
        }
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