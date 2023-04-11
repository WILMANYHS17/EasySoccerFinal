package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.RegisterUsers
import com.example.easysoccer1.domain.SetUsersUseCase

class RegisterUserViewModel(
    private val setUsersUseCase: SetUsersUseCase
) : ViewModel() {


    fun createUser(registerUsers: RegisterUsers) {
        setUsersUseCase.createUser(registerUsers)
    }
}