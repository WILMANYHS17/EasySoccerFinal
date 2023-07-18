package com.example.easysoccer1.ui.view

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.R
import com.example.easysoccer1.databinding.FragmentHomeAdminBinding
import com.example.easysoccer1.databinding.FragmentStatsAdminBinding
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class StatsAdminFragment() : Fragment() {
    private var _binding: FragmentStatsAdminBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatsAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        lifecycleScope.launch {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }


}