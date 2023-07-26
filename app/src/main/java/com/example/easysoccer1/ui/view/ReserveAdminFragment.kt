package com.example.easysoccer1.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.databinding.FragmentReserveAdminBinding
import com.example.easysoccer1.ui.adapter.ReserveAdminAdapter
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import com.example.easysoccer1.ui.viewmodel.ReserveAdminViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReserveAdminFragment : Fragment() {

    private var _binding: FragmentReserveAdminBinding? = null
    private val binding get() = _binding!!

    private val reserveAdminAdapter by lazy {
        ReserveAdminAdapter(
            ::goToReserve
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentReserveAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpAdapter()
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        lifecycleScope.launch {
            val prefs = requireActivity().applicationContext.getSharedPreferences(
                "easySoccer",
                AppCompatActivity.MODE_PRIVATE
            )
            HeaderProfileUser(
                binding!!.headerUser,
                requireContext(),
                headerProfileUserViewModel,
                prefs
            ).build()
            reserveAdminAdapter.setListReserveAdmin(getListReserveAdmin())
        }

        return root
    }

    private suspend fun getListReserveAdmin(): List<Reserve> {
        val reserveAdminViewModel: ReserveAdminViewModel by viewModel()
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val nameSportCenter = prefs.getString("NameSportCenter", "")
        return nameSportCenter?.let {
            reserveAdminViewModel.getListAdminNotificationReserve(it).getOrNull()
        }
            ?: emptyList()
    }

    fun goToReserve(numberReserveAdmin: String) {

    }

    private fun setUpAdapter() {
        binding.recyclerViewNotificationReserveAdmin.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reserveAdminAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}