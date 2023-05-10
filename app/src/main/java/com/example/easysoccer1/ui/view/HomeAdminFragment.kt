package com.example.easysoccer1.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.databinding.FragmentHomeAdminBinding
import com.example.easysoccer1.ui.viewmodel.HomeAdminViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeAdminFragment() : Fragment() {

    private var _binding: FragmentHomeAdminBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root
        lifecycleScope.launch {
            getSportCenter()
            Log.i("Entra", "Si")
        }

        binding.buttonRegisterSportCenter.setOnClickListener { goEditSportCenter() }
        //val textView: TextView = binding.textHome
        // homeAdminViewModel.text.observe(viewLifecycleOwner) {
        //   textView.text = it
        //}
        return root
    }

    fun goEditSportCenter() {
        activity?.let {
            val intent = Intent(this.activity, RegisterSportCenterActivity::class.java)
            intent.putExtra("Edit","Yes")
            startActivity(intent)
        }
    }

    suspend fun getSportCenter() {
        val homeAdminViewModel: HomeAdminViewModel by viewModel()
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailAdmin = prefs.getString("email", "")
        val sportCenter = homeAdminViewModel.getSportCenter(
            SportCenter(
                name = "",
                address = "",
                nit ="",
                price5vs5 = "",
                price8vs8 = "",
                description = "",
                emailAdmin = emailAdmin.toString()
            )
        )
        binding.textNameSportCenter.text = sportCenter.getOrNull()?.name.toString()
        binding.descriptionSportCenter.text = sportCenter.getOrNull()?.description.toString()

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}