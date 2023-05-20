package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.SportCenterUseCase

class SelectSportCenterViewModel(
    private val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {

    suspend fun getListSportCenter(email: String?, nit:String?): Result<List<SportCenter>> {
        return sportCenterUseCase.getListSportCenter(email,nit)
    }
}