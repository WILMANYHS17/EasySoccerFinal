package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.domain.UsersUseCase

class ForgotPasswordViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    fun changePassword(forgotPassword: Users) {
        return usersUseCase.changePassword(forgotPassword)
    }

    suspend fun getUser(emailUser: String): Result<Users> {
        return usersUseCase.searchUser(emailUser)
    }

    fun validationValue(email: String, password : String): Boolean {
        return email.isEmpty() && password.isEmpty()
    }

    fun validationEmail(email : Result<Users>): Boolean{
        return email.isFailure
    }
}