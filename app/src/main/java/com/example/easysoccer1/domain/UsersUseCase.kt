package com.example.easysoccer1.domain

import android.net.Uri
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

    suspend fun searchUser(email: String): Result<Users> {
        return databaseUserRepository.searchUser(email)
    }

    suspend fun getListSportsCenter(
        date: String,
        hour: String,
        optionsFinal: String
    ): Result<List<SportCenter>> {
        return databaseUserRepository.getListSportsCenterUsers(date, hour, optionsFinal)
    }

    fun setImageUser(uriImageUser: Uri, emailUser: String) {
        databaseUserRepository.setImageUser(uriImageUser, emailUser)
    }

    suspend fun getImageUser(email: String): Result<String?> {
        return databaseUserRepository.getImageUser(email)
    }

}