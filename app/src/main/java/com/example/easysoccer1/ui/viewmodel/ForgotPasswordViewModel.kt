package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.domain.UsersUseCase

class ForgotPasswordViewModel(
    private val usersUseCase: UsersUseCase
    ):ViewModel() {

        fun changePassword(forgotPassword: ForgotPassword){
            return usersUseCase.changePassword(forgotPassword)
        }
}