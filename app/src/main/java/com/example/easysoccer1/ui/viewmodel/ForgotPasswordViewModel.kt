package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.ForgotPassword
import com.example.easysoccer1.domain.GetJoinSessionUseCase
import com.example.easysoccer1.domain.SetPasswordUseCase

class ForgotPasswordViewModel(
    private val setPasswordUseCase: SetPasswordUseCase
    ):ViewModel() {

        fun changePassword(forgotPassword: ForgotPassword){

        }
}