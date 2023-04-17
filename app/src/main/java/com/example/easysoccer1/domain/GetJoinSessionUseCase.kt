package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.data.models.RegisterUsers

class GetJoinSessionUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {


    suspend fun joinUser(email: String, password: String) : Result<Boolean>{
    return databaseUserRepository.getUser(email,password)
    }

    suspend fun isAdmin(email:String, isAdmin:Boolean): Result<Boolean>{
        return databaseUserRepository.getIsAdmin(email,isAdmin)
    }

}