package com.example.easysoccer1.ui.homeUser

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easysoccer1.databinding.FragmentHomeUserBinding
import com.example.easysoccer1.models.AreaSportCenterUser
import com.example.easysoccer1.ui.HeaderProfile
import com.example.easysoccer1.ui.HeaderProfileUser
import com.example.easysoccer1.ui.adapter.SportCenterUserAdapter

class HomeUserFragment : Fragment() {

    private var _binding:FragmentHomeUserBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val sportCenterUserAdapter by lazy {
        //SportCenterUserAdapter(
            //::goToDetailProduct,
            //::goToReserveStadium
        //)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeUserViewModel = ViewModelProvider(this)[HomeUserViewModel::class.java]
        _binding = FragmentHomeUserBinding.inflate(inflater, container, false)
        val root: View = binding.root
        HeaderProfileUser(_binding!!.headerUser, this).build()
        homeUserViewModel.text.observe(viewLifecycleOwner) {
        }
        setUpAdapter()
        sportCenterUserAdapter.se(getListStadiumsNearYou())
        homeUserViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    private fun getListStadiumsNearYou(): List<AreaSportCenterUser> {
        return arrayListOf(
            AreaSportCenterUser(
                id = "1",
                image = "sportcenter1",
                directionStadium = "Cl. 15 # 5 - 90, Tunja, Boyacá",
                valueStadium = "90000",
                nameStadium = "Invictus fútbol 5"
            ),
            AreaSportCenterUser(
                id = "2",
                image = "sportcenter2",
                directionStadium = "Cl. 66 # 6-46, Tunja, Boyacá",
                valueStadium = "100000",
                nameStadium = "CANCHAS SINTETICAS TERRA SOCCER"
            ),
        )
    }
    private fun setUpAdapter() {
        binding.recyclerSportCenterNearYou.apply {
           layoutManager = LinearLayoutManager(context)
           adapter = sportCenterUserAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}