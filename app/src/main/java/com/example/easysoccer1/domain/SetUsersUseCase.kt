package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.RegisterUsers

class SetUsersUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    fun createUser(registerUsers: RegisterUsers) {
        databaseUserRepository.createUser(registerUsers)
       // databaseUserRepository.getUser().name == "pedro"
    }


}

