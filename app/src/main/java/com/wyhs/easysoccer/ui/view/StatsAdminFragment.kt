package com.wyhs.easysoccer.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.wyhs.easysoccer.databinding.FragmentStatsAdminBinding
import com.wyhs.easysoccer.ui.viewmodel.HeaderProfileUserViewModel
import com.wyhs.easysoccer.ui.viewmodel.StatsViewModel
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
            numberReservesForDay()
        }


        return root
    }

    private suspend fun numberReservesForDay() {
        val statsViewModel: StatsViewModel by viewModel()
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val nameSportCenter = prefs.getString("NameSportCenter", "")
        /*
        val listDate =
            nameSportCenter?.let { statsViewModel.getDateOfReserves(it).getOrNull() } ?: emptyList()
        for (i in listDate) {
            var date = i.date
            getDayWeek(date)
        }

         */

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
/*
    fun getDayWeek(date: String) {
        val format = SimpleDateFormat("dd-MM-yyyy")
        val todayDate = format.parse(date)

        val calendar = Calendar.getInstance()
        calendar.time = todayDate
        var count = 0
        val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
        if (weekDay == Calendar.MONDAY) {
            count += 1
            binding.mondayData.text = count.toString()
        } else {
            if (weekDay == Calendar.TUESDAY) {
                count += 1
                binding.tuesdayData.text = count.toString()
            } else {
                if (weekDay == Calendar.WEDNESDAY) {
                    count += 1
                    binding.wednesdayData.text = count.toString()
                } else {
                    if (weekDay == Calendar.THURSDAY) {
                        count += 1
                        binding.thursdayData.text = count.toString()
                    } else {
                        if (weekDay == Calendar.FRIDAY) {
                            count += 1
                            binding.fridayData.text = count.toString()
                        } else {
                            if (weekDay == Calendar.SATURDAY) {
                                count += 1
                                binding.saturdayData.text = count.toString()
                            } else {
                                if (weekDay == Calendar.SUNDAY) {
                                    count += 1
                                    binding.sundayData.text = count.toString()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

 */

}