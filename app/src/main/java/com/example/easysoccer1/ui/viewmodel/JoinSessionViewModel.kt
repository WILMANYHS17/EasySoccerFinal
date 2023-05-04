package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.JoinSessionUsers
import com.example.easysoccer1.data.models.RegisterUsers
import com.example.easysoccer1.domain.GetJoinSessionUseCase

class JoinSessionViewModel(
    private val getJoinSessionUseCase: GetJoinSessionUseCase
) : ViewModel() {


    suspend fun searchUser(joinSessionUsers: JoinSessionUsers): Result<Boolean>{
        return getJoinSessionUseCase.joinUser(joinSessionUsers.email, joinSessionUsers.password)
    }

    suspend fun isAdmin(joinSessionUsers: JoinSessionUsers):Result<Boolean>{
        return getJoinSessionUseCase.isAdmin(joinSessionUsers.email, joinSessionUsers.isAdmin)
    }

}