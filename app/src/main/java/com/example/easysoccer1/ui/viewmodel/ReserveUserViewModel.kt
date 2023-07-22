package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.ReserveUseCase
import com.example.easysoccer1.domain.SportCenterUseCase

class ReserveUserViewModel(
    private val reserveUseCase: ReserveUseCase,
    private val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {
    fun setReserve(reserve: Reserve, emailUser: String?) {
        reserveUseCase.setReserve(reserve, emailUser)
    }

    suspend fun getSportCenter(nit: String): Result<SportCenter> {
        return sportCenterUseCase.getSportCenterUser(nit)
    }

}