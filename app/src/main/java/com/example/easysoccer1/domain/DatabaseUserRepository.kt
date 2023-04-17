package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.data.models.RegisterUsers

interface DatabaseUserRepository {


    fun createUser(registerUsers: RegisterUsers)

    suspend fun getUser(email: String, password:String) : Result<Boolean>



    suspend fun getIsAdmin(email:String, isAdmin:Boolean):Result<Boolean>

    fun changePassword(forgotPassword: ForgotPassword)
}