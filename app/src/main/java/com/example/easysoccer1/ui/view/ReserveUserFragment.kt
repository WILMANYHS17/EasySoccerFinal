package com.example.easysoccer1.ui.view

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.databinding.FragmentReserveAdminBinding
import com.example.easysoccer1.databinding.FragmentReserveUserBinding
import com.example.easysoccer1.ui.adapter.ReserveUserAdapter
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import com.example.easysoccer1.ui.viewmodel.ReserveUserFragmentViewModel
import com.example.easysoccer1.ui.viewmodel.ReserveUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReserveUserFragment : Fragment() {

    private var _binding: FragmentReserveUserBinding? = null
    private val binding get() = _binding!!
    private val reserveUserAdapter by lazy {
        ReserveUserAdapter(
            ::goToReserve
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReserveUserBinding.inflate(layoutInflater)
        val root: View = binding.root
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        setUpAdapter()
        lifecycleScope.launch {
            reserveUserAdapter.setListReserveUser(getListReservations())
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

    suspend fun getListReservations(): List<Reserve> {
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailUser = prefs.getString("email", "")
        val reserveUserFragmentViewModel: ReserveUserFragmentViewModel by viewModel()
        return reserveUserFragmentViewModel.getListReserveUser(emailUser).getOrNull() ?: emptyList()
    }

    fun setUpAdapter() {
        binding.recyclerViewNotificationReserveUser.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reserveUserAdapter
        }
    }

    fun goToReserve(number: String) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}