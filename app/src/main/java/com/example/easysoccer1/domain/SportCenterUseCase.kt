package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.SportCenter

class SportCenterUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    suspend fun getSportCenter(sportCenter:SportCenter): Result<SportCenter> {
        return databaseUserRepository.getSportCenter(sportCenter)
    }

    fun setSportCenter(sportCenter:SportCenter){
        databaseUserRepository.createSportCenter(sportCenter)
    }

}