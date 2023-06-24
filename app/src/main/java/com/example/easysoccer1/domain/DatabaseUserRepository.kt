package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.data.models.Goals
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.data.models.SportCenter

interface DatabaseUserRepository {

    fun createUser(users: Users)
    suspend fun getUser(email: String, password: String): Result<Boolean>
    suspend fun getIsAdmin(email: String, isAdmin: Boolean): Result<Boolean>
    fun changePassword(forgotPassword: ForgotPassword)
    fun createSportCenter(sportCenter:SportCenter)
    suspend fun getSportCenter(nit: String,email:String):Result<SportCenter>
    suspend fun searchUser(email:String): Result<Users>
    suspend fun getListSportCenter(email: String?):Result<List<SportCenter>>
    suspend fun getListGoals(emailAdmin:String?, nit:String?):Result<List<Goals>>
    fun setGoals(goals:Goals, emailAdmin:String?, nit:String?)

}