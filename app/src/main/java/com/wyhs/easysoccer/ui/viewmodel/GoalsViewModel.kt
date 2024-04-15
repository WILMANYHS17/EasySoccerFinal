package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Goals
import com.wyhs.easysoccer.domain.GoalsUseCase

class GoalsViewModel(
    private val goalsUseCase: GoalsUseCase
) : ViewModel() {

    fun setGoal(goals: Goals, emailAdmin: String?, nit: String?) {
        goalsUseCase.setGoals(goals, emailAdmin, nit)
    }

    suspend fun getGoalAdmin(nit: String?, number: String): Result<Goals> {
        return goalsUseCase.getGoalAdmin(nit, number)
    }

    suspend fun getListGoals(email: String?, nit: String?): Result<List<Goals>> {
        return goalsUseCase.getListGoals(email, nit)
    }

    fun deleteGoal(emailAdmin: String?, nit: String?, number: String) {
        goalsUseCase.deleteGoal(emailAdmin, nit, number)
    }

    fun validateSize(size: String): Boolean {
        return size == "5vs5"
    }


}