package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Goals
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.GoalsUseCase
import com.example.easysoccer1.domain.ReserveUseCase
import com.example.easysoccer1.domain.SportCenterUseCase

class ReserveUserViewModel(
    private val reserveUseCase: ReserveUseCase,
    private val sportCenterUseCase: SportCenterUseCase,
    private val goalsUseCase: GoalsUseCase
) : ViewModel() {
    fun setReserve(reserve: Reserve, emailUser: String?) {
        reserveUseCase.setReserve(reserve, emailUser)
    }

    suspend fun getSportCenter(nit: String): Result<SportCenter> {
        return sportCenterUseCase.getSportCenterUser(nit)
    }

    suspend fun getGoal(nit: String, size: String): Result<Goals> {
        return goalsUseCase.getGoal(nit, size)
    }

    suspend fun updateGoal(updateGoal: Goals, number: String, nit: String) {
        goalsUseCase.updateGoal(updateGoal, number, nit)
    }

}