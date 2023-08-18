package com.example.easysoccer1.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.UsersUseCase

class RegisterUserViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    fun createUser(users: Users) {
        usersUseCase.createUser(users)
    }

    fun setImageUser(uriImageUser: Uri, emailUser: String) {
        usersUseCase.setImageUser(uriImageUser, emailUser)
    }

    suspend fun getUser(emailUser: String): Result<Users> {
        return usersUseCase.getUser(emailUser)
    }
}