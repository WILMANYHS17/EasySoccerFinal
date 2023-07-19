package com.example.easysoccer1.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.UsersUseCase

class RegisterUserViewModel(
    private val usersUseCase: UsersUseCase
) : ViewModel() {

    fun createUser(users: Users) {
        usersUseCase.createUser(users)
    }
}