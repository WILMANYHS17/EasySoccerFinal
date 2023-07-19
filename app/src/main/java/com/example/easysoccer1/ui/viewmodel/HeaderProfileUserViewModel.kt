package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.UsersUseCase

class HeaderProfileUserViewModel(
    val usersUseCase: UsersUseCase
) : ViewModel() {
    suspend fun getNameUser(email: String): Result<Users> {
        return usersUseCase.searchUser(email)
    }
}