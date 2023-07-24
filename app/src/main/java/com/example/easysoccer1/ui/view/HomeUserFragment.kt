package com.example.easysoccer1.ui.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.R
import com.example.easysoccer1.databinding.FragmentHomeUserBinding
import com.example.easysoccer1.data.models.SportCenter
//import com.example.easysoccer1.ui.view.HeaderProfileUser
import com.example.easysoccer1.ui.adapter.SportCenterUserAdapter
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import com.example.easysoccer1.ui.viewmodel.HomeUserViewModel
import com.wilman.easysoccer.ui.calendarUser.DatePickerFragment
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
        val hourFinal = changeColorButton()
        binding.buttonSearch.setOnClickListener {
            lifecycleScope.launch {
                centerUserAdapter.setListInYouArea(getListStadiumsNearYou(hourFinal))
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
        }


        binding.date.setOnClickListener { showDatePickerDialog() }


        return root
    }

    private fun changeColorButton(button: Button): String {
        var isClicked = false
        var hour = ""
        button.setOnClickListener {
            if (!isClicked) {
                binding.hour12.setBackgroundColor(Color.GREEN)
                hour = binding.hour12.text.toString()
                isClicked = true
            } else {
                binding.hour12.setBackgroundResource(R.drawable.custom_button_hour)
                hour = ""
                isClicked = false
            }
        }
        return hour
    }

    private suspend fun getListStadiumsNearYou(hour: String): List<SportCenter> {
        val homeUserViewModel: HomeUserViewModel by viewModel()
        val listOptions = listOf("5vs5", "8vs8")
        val options = binding.spinnerSizePlayers
        var optionsFinal = ""
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
                optionsFinal = options.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        val date = binding.date.text.toString()

        return homeUserViewModel.getListSportsCenter(date, hour, optionsFinal).getOrNull()
            ?: emptyList()
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

    fun showDatePickerDialog() {
        val datePicker = DatePickerFragment { day, month, year -> onDateSelect(day, month, year) }
        datePicker.show(childFragmentManager, "datePicker")
    }

    fun onDateSelect(day: Int, month: Int, year: Int) {
        binding.date.setText("$day / $month / $year")
    }

}