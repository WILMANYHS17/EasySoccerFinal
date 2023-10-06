package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.domain.UsersUseCase

class JoinSessionViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    suspend fun searchUsers1(email: String): Result<Users> {
        return usersUseCase.searchUser(email)
    }

}