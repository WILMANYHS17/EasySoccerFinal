package com.example.easysoccer1.ui.view

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.databinding.FragmentHomeAdminBinding
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import com.example.easysoccer1.ui.viewmodel.HomeAdminViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeAdminFragment() : Fragment() {

    private var _binding: FragmentHomeAdminBinding? = null
    private lateinit var nit: String
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nit = activity?.intent?.extras?.getString("Nit") ?: ""
        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()


        _binding = FragmentHomeAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root
        lifecycleScope.launch {
            val prefs = requireActivity().applicationContext.getSharedPreferences(
                "easySoccer",
                AppCompatActivity.MODE_PRIVATE
            )
            HeaderProfileUser(_binding!!.headerUser, requireContext(), headerProfileUserViewModel, prefs).build()
            getSportCenter(nit,prefs)
            Log.i("Entra", "Si")
        }
        binding.buttonEditSportCenter.setOnClickListener { goEditSportCenter() }
        return root
    }

    fun goEditSportCenter() {
        activity?.let {
            val intent = Intent(this.activity, RegisterSportCenterActivity::class.java)
            intent.putExtra("Edit","Yes")
            intent.putExtra("Nit", nit)
            startActivity(intent)
        }
    }

    suspend fun getSportCenter(nit: String, prefs: SharedPreferences) {
        val homeAdminViewModel: HomeAdminViewModel by viewModel()

        val editor= requireActivity().getSharedPreferences("easySoccer", MODE_PRIVATE).edit()
        editor.putString("Nit", nit)
        editor.apply()
        val emailAdmin = prefs.getString("email", "")
        val sportCenter = nit?.let { emailAdmin?.let { it1 ->
            homeAdminViewModel.getSportCenter(it,
                it1
            )
        } }
        binding.textNameSportCenter.text = sportCenter?.getOrNull()?.nameSportCenter.toString()
        binding.descriptionSportCenter.text = sportCenter?.getOrNull()?.description.toString()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}