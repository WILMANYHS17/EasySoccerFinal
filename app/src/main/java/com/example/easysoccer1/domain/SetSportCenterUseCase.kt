package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.SportCenter

class SetSportCenterUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    fun setSportCenter(sportCenter:SportCenter){
        databaseUserRepository.createSportCenter(sportCenter)
    }
}