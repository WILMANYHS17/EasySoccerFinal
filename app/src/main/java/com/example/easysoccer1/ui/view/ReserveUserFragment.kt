package com.example.easysoccer1.ui.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.easysoccer1.R
import com.example.easysoccer1.ui.viewmodel.ReserveUserViewModel

class ReserveUserFragment : Fragment() {

    companion object {
        fun newInstance() = ReserveUserFragment()
    }

    private lateinit var viewModel: ReserveUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reserve_user, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ReserveUserViewModel::class.java)
        // TODO: Use the ViewModel
    }

}