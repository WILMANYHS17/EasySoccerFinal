package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.UsersUseCase

class ForgotPasswordViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    fun changePassword(forgotPassword: Users) {
        return usersUseCase.changePassword(forgotPassword)
    }

    suspend fun getUser(emailUser: String): Result<Users> {
        return usersUseCase.searchUser(emailUser)
    }
}