package com.example.easysoccer1.domain

import com.example.easysoccer1.data.database.DataBase

class SetUsersUseCase {
    private val user = DataBase()
    operator fun invoke() {
    user.CreateUser()
    }
}