package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.RegisterUsers

interface DatabaseUserRepository {
    fun createUser(registerUsers: RegisterUsers)
    fun getUser(id: String) : RegisterUsers
}