package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.Goals

class GoalsUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    fun setGoals(goals: Goals, emailAdmin: String?, nit: String?) {
        databaseUserRepository.setGoals(goals, emailAdmin, nit)
    }

    suspend fun getListGoals(emailAdmin: String?, nit: String?): Result<List<Goals>> {
        return databaseUserRepository.getListGoals(emailAdmin, nit)
    }

    fun deleteGoal(emailAdmin: String?, nit: String?, number: String) {
        databaseUserRepository.deleteGoal(emailAdmin, nit, number)
    }

    suspend fun getGoal(nit: String, size: String): Result<Goals> {
        return databaseUserRepository.getGoal(nit, size)
    }

    suspend fun updateGoal(updateGoal: Goals, number: Int, nit: String) {
        databaseUserRepository.updateGoal(updateGoal, number, nit)
    }
}