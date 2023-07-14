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

        lifecycleScope.launch {
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
        activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Crear cancha")
                setMessage("¿Está seguro de crear esta cancha?")
                setPositiveButton("Si") { _: DialogInterface, _: Int ->

                    goalsViewModel.setGoal(
                        Goals(
                            nameOrNumber = binding.inputTextNumberGoal.text.toString(),
                            size = binding.editTextSizeGoal.text.toString(),
                            price = "",
                            available = "Disponible",
                            hour = "",
                            date = ""
                        ), emailAdmin, nit
                    )
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