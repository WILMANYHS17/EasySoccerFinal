package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.data.models.Users

class UsersUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {

    suspend fun joinUser(email: String, password: String) : Result<Boolean>{
    return databaseUserRepository.getUser(email,password)
    }

    suspend fun isAdmin(email:String, isAdmin:Boolean): Result<Boolean>{
        return databaseUserRepository.getIsAdmin(email,isAdmin)
    }

    fun createUser(users: Users) {
        databaseUserRepository.createUser(users)
    }

    fun changePassword(forgotPassword: Users) {
        return databaseUserRepository.changePassword(forgotPassword)
    }
    suspend fun searchUser(email: String): Result<Users>{
        return databaseUserRepository.searchUser(email)
    }
    suspend fun getListSportsCenter(): Result<List<SportCenter>>{
        return databaseUserRepository.getListSportsCenterUsers()
    }

}