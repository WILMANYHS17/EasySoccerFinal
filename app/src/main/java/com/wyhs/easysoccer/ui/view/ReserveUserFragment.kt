package com.wyhs.easysoccer.ui.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wyhs.easysoccer.data.models.Reserve
import com.wyhs.easysoccer.databinding.FragmentReserveUserBinding
import com.wyhs.easysoccer.ui.adapter.ReserveUserAdapter
import com.wyhs.easysoccer.ui.viewmodel.HeaderProfileUserViewModel
import com.wyhs.easysoccer.ui.viewmodel.ReserveUserFragmentViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReserveUserFragment : Fragment() {

    private var _binding: FragmentReserveUserBinding? = null
    val reserveUserFragmentViewModel: ReserveUserFragmentViewModel by viewModel()
    private val binding get() = _binding!!
    private val reserveUserAdapter by lazy {
        ReserveUserAdapter(
            ::cancelReserve, ::updateGoal
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

        return reserveUserFragmentViewModel.getListReserveUser(emailUser).getOrNull() ?: emptyList()
    }

    fun setUpAdapter() {
        binding.recyclerViewNotificationReserveUser.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = reserveUserAdapter
        }
    }

    fun cancelReserve(number: String) {
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailUser = prefs.getString("email", "")
        activity.let {
            if (it != null) {
                AlertDialog.Builder(it).apply{
                    setTitle("Cancelar reserva")
                    setMessage("¿Está seguro de cancelar la reserva?")
                    setPositiveButton("Si"){_: DialogInterface, _: Int ->
                        lifecycleScope.launch {
                            reserveUserFragmentViewModel.cancelReserve(number,emailUser)
                        }
                    }
                    setNegativeButton("No", null)
                }.show()
            }

        }

    }

    fun updateGoal (numberGoal: String){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}