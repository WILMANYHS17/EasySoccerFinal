package com.example.easysoccer1.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.easysoccer1.databinding.FragmentReserveAdminBinding
import com.example.easysoccer1.ui.viewmodel.ReserveUserViewModel

class ReserveUserFragment : Fragment() {

    private var _binding: FragmentReserveAdminBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: ReserveUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReserveAdminBinding.inflate(layoutInflater)
        val root: View = binding.root

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReserveUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}