package com.example.easysoccer1.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.databinding.FragmentHomeUserBinding
import com.example.easysoccer1.data.models.SportCenter
//import com.example.easysoccer1.ui.view.HeaderProfileUser
import com.example.easysoccer1.ui.adapter.SportCenterUserAdapter
import com.example.easysoccer1.ui.viewmodel.HomeUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeUserFragment : Fragment() {

    private var _binding: FragmentHomeUserBinding? = null
    private val binding get() = _binding!!
    private val centerUserAdapter by lazy {
        SportCenterUserAdapter(
            ::goToDescription
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeUserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        HeaderProfileUser(_binding!!.headerUser, this).build()

        setUpAdapter()
        lifecycleScope.launch {
            centerUserAdapter.setListInYouArea(getListStadiumsNearYou())
        }


        return root
    }

    private suspend fun getListStadiumsNearYou(): List<SportCenter> {
        val homeUserViewModel: HomeUserViewModel by viewModel()
        return homeUserViewModel.getListSportsCenter().getOrNull() ?: emptyList()
    }

    private fun setUpAdapter() {
        binding.recyclerSportCenterNearYou.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = centerUserAdapter
        }
    }

    private fun goToDescription(nit: String) {
        activity?.let {
            val intent = Intent(this.activity, DescriptionSportCenterActivity::class.java)
            intent.putExtra("Nit", nit)
            it.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}