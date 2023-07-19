package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Goals
import com.example.easysoccer1.domain.GoalsUseCase

class GoalsViewModel(
    private val goalsUseCase: GoalsUseCase
) : ViewModel() {

    fun setGoal(goals: Goals, emailAdmin: String?, nit: String?) {
        goalsUseCase.setGoals(goals, emailAdmin, nit)
    }

    suspend fun getListGoals(email: String?, nit: String?): Result<List<Goals>> {
        return goalsUseCase.getListGoals(email, nit)
    }

    fun deleteGoal(emailAdmin: String?, nit: String?, number: String) {
        goalsUseCase.deleteGoal(emailAdmin, nit, number)
    }

}