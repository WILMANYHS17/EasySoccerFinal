package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.UsersUseCase

class JoinSessionViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    suspend fun searchUsers1(email:String): Result<Users>{
        return usersUseCase.searchUser(email)
    }

}