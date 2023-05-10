package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.GetSportCenterUseCase
import com.example.easysoccer1.domain.SetSportCenterUseCase

class RegisterSportCenterViewModel(
    private val setSportCenterUseCase: SetSportCenterUseCase,
    private val getSportCenterUseCase: GetSportCenterUseCase
): ViewModel() {


    fun setSportCenter(sportCenter:SportCenter){
        setSportCenterUseCase.setSportCenter(sportCenter)
    }

    suspend fun getSportCenter(sportCenter:SportCenter):Result<SportCenter>{
        return getSportCenterUseCase.getSportCenter(sportCenter)
    }

}