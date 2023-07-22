package com.example.easysoccer1.ui.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.data.models.Goals
import com.example.easysoccer1.databinding.FragmentGoalsBinding
import com.example.easysoccer1.ui.adapter.GoalsAdminAdapter
import com.example.easysoccer1.ui.viewmodel.GoalsViewModel
import com.example.easysoccer1.ui.viewmodel.HeaderProfileUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoalsFragment : Fragment() {

    private var _binding: FragmentGoalsBinding? = null
    private val binding get() = _binding!!

    private val goalsAdminAdapter by lazy {
        GoalsAdminAdapter(
            ::deleteGoals
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGoalsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setUpAdapter()
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
            goalsAdminAdapter.setListGoals(getListGoals())

        }
        binding.bottonCreateGoal.setOnClickListener { onClickCreateGoals() }
        return root
    }

    fun onClickCreateGoals() {
        val goalsViewModel: GoalsViewModel by viewModel()
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )

        val emailAdmin = prefs.getString("email", "")
        val nit = prefs.getString("Nit", "")
        val price5vs5 = prefs.getString("Price5vs5", "")
        val price8vs8 = prefs.getString("Price8vs8", "")
        val size = binding.editTextSizeGoal.text.toString()

        activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Crear cancha")
                setMessage("¿Está seguro de crear esta cancha?")
                setPositiveButton("Si") { _: DialogInterface, _: Int ->
                    if (size == "5vs5") {
                        price5vs5?.let { it1 ->
                            Goals(
                                number = binding.inputTextNumberGoal.text.toString(),
                                size = size,
                                price = it1,
                                available = "Disponible",
                                hour = "",
                                date = ""
                            )
                        }?.let { it2 ->
                            goalsViewModel.setGoal(
                                it2, emailAdmin, nit
                            )
                        }
                    } else {
                        price8vs8?.let { it1 ->
                            Goals(
                                number = binding.inputTextNumberGoal.text.toString(),
                                size = size,
                                price = it1,
                                available = "Disponible",
                                hour = "",
                                date = ""
                            )
                        }?.let { it2 ->
                            goalsViewModel.setGoal(
                                it2, emailAdmin, nit
                            )
                        }
                    }

                }
                setNegativeButton("No", null)
            }.show()
        }

    }

    suspend fun getListGoals(): List<Goals> {
        val goalsViewModel: GoalsViewModel by viewModel()
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailAdmin = prefs.getString("email", "")
        val nit = prefs.getString("Nit", "")
        return goalsViewModel.getListGoals(emailAdmin, nit).getOrNull() ?: emptyList()
    }

    fun deleteGoals(number: String) {
        val goalsViewModel: GoalsViewModel by viewModel()
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailAdmin = prefs.getString("email", "")
        val nit = prefs.getString("Nit", "")
        goalsViewModel.deleteGoal(emailAdmin, nit, number)
    }

    fun setUpAdapter() {
        binding.recyclerViewGoals.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = goalsAdminAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



