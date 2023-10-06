package com.wyhs.easysoccer.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.domain.UsersUseCase

class RegisterUserViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    fun createUser(users: Users) {
        usersUseCase.createUser(users)
    }

    suspend fun setImageUser(uriImageUser: Uri, emailUser: String) {
        usersUseCase.setImageUser(uriImageUser, emailUser)
    }

    suspend fun getUser(emailUser: String): Result<Users> {
        return usersUseCase.getUser(emailUser)
    }

    suspend fun getImageUser(emailUser: String): Result<String?> {
        return usersUseCase.getImageUser(emailUser)
    }
}