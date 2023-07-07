package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.domain.SportCenterUseCase

class DescriptionSportCenterViewModel(
    val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {
    fun getSportCenterUser(nit: String?) {
        sportCenterUseCase.getSportCenterUser(nit)
    }
}