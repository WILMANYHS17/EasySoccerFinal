package com.example.easysoccer1.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.databinding.FragmentHomeUserBinding
import com.example.easysoccer1.data.models.SportCenter
//import com.example.easysoccer1.ui.view.HeaderProfileUser
import com.example.easysoccer1.ui.adapter.SportCenterUserAdapter
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
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
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()

        setUpAdapter()
        lifecycleScope.launch {
            centerUserAdapter.setListInYouArea(getListStadiumsNearYou())
            val prefs = requireActivity().applicationContext.getSharedPreferences(
                "easySoccer",
                AppCompatActivity.MODE_PRIVATE
            )
            HeaderProfileUser(
                _binding!!.headerUser,
                requireContext(),
                headerProfileUserViewModel,
                prefs
            ).build()
        }

        return root
    }

    private suspend fun getListStadiumsNearYou(): List<SportCenter> {
        val homeUserViewModel: HomeUserViewModel by viewModel()
        val listOptions = listOf("5vs5", "8vs8")
        val options = binding.spinnerSizePlayers
        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, listOptions)
        options.adapter = adapter
        options.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                options.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
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
            intent.putExtra("Nit1", nit)
            it.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}