package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.domain.UsersUseCase

class HeaderProfileUserViewModel(
    val usersUseCase: UsersUseCase
) : ViewModel() {
    suspend fun getNameUser(email: String): Result<Users> {
        return usersUseCase.searchUser(email)
    }

    suspend fun getImageUser(email: String): Result<String?> {
    return usersUseCase.getImageUser(email)
    }
}