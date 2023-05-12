package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.SportCenterUseCase

class RegisterSportCenterViewModel(
    private val sportCenterUseCase: SportCenterUseCase
): ViewModel() {


    fun setSportCenter(sportCenter:SportCenter){
        sportCenterUseCase.setSportCenter(sportCenter)
    }

}