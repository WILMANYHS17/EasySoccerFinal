package com.example.easysoccer1.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.easysoccer1.data.models.Goal
import com.example.easysoccer1.databinding.FragmentGoalsBinding
import com.example.easysoccer1.ui.viewmodel.GoalsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoalsFragment : Fragment() {

    private var _binding: FragmentGoalsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.bottonCreateGoal.setOnClickListener { createGoal() }

        _binding = FragmentGoalsBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun createGoal() {
        val goalsViewModel: GoalsViewModel by viewModel()
        goalsViewModel.createGoal(
            Goal(
                id = binding.inputTextNumberGoal.text.toString(),
                price = binding.editTextPrice.text.toString(),
                size = binding.editTextSizeGoal.text.toString()
                )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}