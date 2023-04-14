package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.JoinSessionUsers

class JoinSessionViewModel: ViewModel() {
    val joinSessionUsersModel = MutableLiveData<JoinSessionUsers>()

    fun searchUser(){
        //val userExist: JoinSessionUsers =
    }
}