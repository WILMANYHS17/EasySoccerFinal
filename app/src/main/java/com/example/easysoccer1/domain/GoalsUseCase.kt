package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.Goals

class GoalsUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    fun setGoals(goals:Goals, emailAdmin:String?, nit:String?){
        databaseUserRepository.setGoals(goals, emailAdmin, nit)
    }

    suspend fun getListGoals(emailAdmin:String?, nit:String?): Result<List<Goals>> {
        return databaseUserRepository.getListGoals(emailAdmin,nit)
    }
}