package com.wyhs.easysoccer.domain

import android.net.Uri
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.data.models.Users

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

    suspend fun setImageUser(uriImageUser: Uri, emailUser: String) {
        databaseUserRepository.setImageUser(uriImageUser, emailUser)
    }

    suspend fun getImageUser(email: String): Result<String?> {
        return databaseUserRepository.getImageUser(email)
    }

    suspend fun getUser(emailUser: String): Result<Users> {
        return databaseUserRepository.getUserComplete(emailUser)
    }

}