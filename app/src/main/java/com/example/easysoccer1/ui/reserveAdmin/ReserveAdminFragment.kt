package com.example.easysoccer1.ui.reserveAdmin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.easysoccer1.databinding.FragmentReserveAdminBinding

class ReserveAdminFragment : Fragment() {

    private var _binding: FragmentReserveAdminBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val reserveAdminViewModel =
            ViewModelProvider(this).get(ReserveAdminViewModel::class.java)

        _binding = FragmentReserveAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textReserveAdmin
        reserveAdminViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}