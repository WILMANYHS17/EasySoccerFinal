package com.example.easysoccer1.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.SportCenterUseCase

class HomeAdminViewModel(
    private val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {
    suspend fun getSportCenter(nit: String, email: String): Result<SportCenter> {
        return sportCenterUseCase.getSportCenter(nit, email)
    }

}

