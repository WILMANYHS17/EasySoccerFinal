package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Goals
import com.wyhs.easysoccer.data.models.Reserve
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.GoalsUseCase
import com.wyhs.easysoccer.domain.ReserveUseCase
import com.wyhs.easysoccer.domain.SportCenterUseCase

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