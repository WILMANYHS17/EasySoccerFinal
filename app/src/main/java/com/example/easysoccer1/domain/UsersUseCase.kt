package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.data.models.Users

class UsersUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {

    fun createUser(users: Users) {
        databaseUserRepository.createUser(users)
    }
    fun changePassword(forgotPassword: Users) {
        return databaseUserRepository.changePassword(forgotPassword)
    }
    suspend fun searchUser(email: String): Result<Users>{
        return databaseUserRepository.searchUser(email)
    }
    suspend fun getListSportsCenter(date: String, hour: String, optionsFinal: String): Result<List<SportCenter>>{
        return databaseUserRepository.getListSportsCenterUsers(date, hour, optionsFinal)
    }

}