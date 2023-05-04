package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.data.models.RegisterUsers
import com.example.easysoccer1.data.models.SportCenter

interface DatabaseUserRepository {

    fun createUser(registerUsers: RegisterUsers)
    suspend fun getUser(email: String, password: String): Result<Boolean>
    suspend fun getIsAdmin(email: String, isAdmin: Boolean): Result<Boolean>
    fun changePassword(forgotPassword: ForgotPassword)
    fun createSportCenter(sportCenter:SportCenter)
    suspend fun getSportCenter(sportCenter:SportCenter):Result<SportCenter>

}