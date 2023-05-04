package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.SportCenter

class GetSportCenterUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    suspend fun getSportCenter(sportCenter:SportCenter): Result<SportCenter> {
        return databaseUserRepository.getSportCenter(sportCenter)
    }

}