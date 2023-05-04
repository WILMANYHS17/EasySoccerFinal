package com.example.easysoccer1.ui.homeAdmin


import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.GetSportCenterUseCase

class HomeAdminViewModel(
    private val getSportCenterUseCase: GetSportCenterUseCase
) : ViewModel() {
    suspend fun getSportCenter(sportCenter: SportCenter) : Result<SportCenter>{
    return getSportCenterUseCase.getSportCenter(sportCenter)
    }

}

