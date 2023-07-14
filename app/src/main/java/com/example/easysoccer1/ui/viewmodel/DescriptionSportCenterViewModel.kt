package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.SportCenterUseCase

class DescriptionSportCenterViewModel(
    val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {
    suspend fun getSportCenterUser(nit: String?): Result<SportCenter> {
        return sportCenterUseCase.getSportCenterUser(nit)
    }
}