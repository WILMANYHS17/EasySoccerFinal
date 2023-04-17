package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.ForgotPassword

class SetPasswordUseCase (
    private val databaseUserRepository: DatabaseUserRepository
        ){
    fun changePassword(forgotPassword: ForgotPassword){

    }

}