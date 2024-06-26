package com.wyhs.easysoccer.ui.view

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
import com.wyhs.easysoccer.data.models.Goals
import com.wyhs.easysoccer.databinding.FragmentGoalsBinding
import com.wyhs.easysoccer.ui.adapter.GoalsAdminAdapter
import com.wyhs.easysoccer.ui.viewmodel.GoalsViewModel
import com.wyhs.easysoccer.ui.viewmodel.HeaderProfileUserViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoalsFragment : Fragment() {

    private var _binding: FragmentGoalsBinding? = null
    private val goalsViewModel: GoalsViewModel by viewModel()
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

        val headerProfileUserViewModel: HeaderProfileUserViewModel by viewModel()
        setUpAdapter()
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

        binding.bottonCreateGoal.setOnClickListener {
            lifecycleScope.launch {
                onClickCreateGoals()
            }
        }
        return root
    }

    suspend fun onClickCreateGoals() {

        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )

        val emailAdmin: String? = prefs.getString("email", "")
        val nit: String? = prefs.getString("Nit", "")
        val price5vs5: String? = prefs.getString("Price5vs5", "")

        val price8vs8: String? = prefs.getString("Price8vs8", "")
        val size = binding.editTextSizeGoal.text.toString()
        val number = binding.inputTextNumberGoal.text.toString()
        if (number.isEmpty() && size.isEmpty()) {
            binding.editTextSizeGoal.error = "La casilla está vacía"
            binding.inputTextNumberGoal.error = "La casilla está vacía"
        } else {
            if (size.isEmpty()) {
                binding.editTextSizeGoal.error = "La casilla está vacía"
            } else {
                if (number.isEmpty()) {
                    binding.inputTextNumberGoal.error = "La casilla está vacía"
                } else {
                    if (goalsViewModel.getGoalAdmin(nit, number).isSuccess) {
                        binding.inputTextNumberGoal.error = "Esa cancha ya existe"
                    } else {
                        requireActivity().let {
                            AlertDialog.Builder(it).apply {
                                setTitle("Crear cancha")
                                setMessage("¿Está seguro de crear esta cancha?")
                                setPositiveButton("Si") { _: DialogInterface, _: Int ->
                                    if (goalsViewModel.validateSize(size)) {
                                        val goals = price5vs5?.let { it1 ->
                                            Goals(
                                                number = number,
                                                size = size,
                                                price = it1,
                                                available = "Disponible",
                                                hour = "",
                                                date = ""
                                            )
                                        }
                                        if (goals != null) {
                                            goalsViewModel.setGoal(
                                                goals, emailAdmin, nit
                                            )
                                        }
                                    } else {
                                        val goals = price8vs8?.let { it1 ->
                                            Goals(
                                                number = binding.inputTextNumberGoal.text.toString(),
                                                size = size,
                                                price = it1,
                                                available = "Disponible",
                                                hour = "",
                                                date = ""
                                            )
                                        }
                                        if (goals != null) {
                                            goalsViewModel.setGoal(
                                                goals, emailAdmin, nit
                                            )
                                        }
                                    }
                                }
                                setNegativeButton("No", null)
                            }.show()
                        }
                    }
                }
            }
        }

        getListGoals()
    }

    suspend fun getListGoals(): List<Goals> {
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailAdmin = prefs.getString("email", "")
        val nit = prefs.getString("Nit", "")
        return goalsViewModel.getListGoals(emailAdmin, nit).getOrNull() ?: emptyList()
    }

    fun deleteGoals(number: String) {
        val prefs = requireActivity().applicationContext.getSharedPreferences(
            "easySoccer",
            AppCompatActivity.MODE_PRIVATE
        )
        val emailAdmin = prefs.getString("email", "")
        val nit = prefs.getString("Nit", "")
        requireActivity().let {
            AlertDialog.Builder(it).apply {
                setTitle("Eliminar cancha")
                setMessage("¿Está seguro de eliminar la cancha?")
                setPositiveButton("Si") { _: DialogInterface, _: Int ->
                    goalsViewModel.deleteGoal(emailAdmin, nit, number)
                }
                setNegativeButton("No", null)
            }.show()
        }
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



