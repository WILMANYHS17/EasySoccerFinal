package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.UsersUseCase

class JoinSessionViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {


    suspend fun searchUser(joinSessionUsers: JoinSessionUsers): Result<Boolean>{
        return usersUseCase.joinUser(joinSessionUsers.email, joinSessionUsers.password)
    }

    suspend fun isAdmin(joinSessionUsers: JoinSessionUsers):Result<Boolean>{
        return usersUseCase.isAdmin(joinSessionUsers.email, joinSessionUsers.isAdmin)
    }
    suspend fun searchUsers1(email:String): Result<Users>{
        return usersUseCase.searchUser(email)
    }

}