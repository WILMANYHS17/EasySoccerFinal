package com.wyhs.easysoccer.domain

import com.wyhs.easysoccer.data.models.Goals

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

    suspend fun updateGoal(updateGoal: Goals, number: String, nit: String) {
        databaseUserRepository.updateGoal(updateGoal, number, nit)
    }

    suspend fun getGoalAdmin(nit: String?, number: String): Result<Goals> {
        return databaseUserRepository.getGoalAdmin(nit, number)
    }
}