package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.database.DataBase
import com.example.easysoccer1.data.models.RegisterUsers

class RegisterUserViewModel: ViewModel() {

val registerUserModel = MutableLiveData<RegisterUsers>()

    fun CreateUsers(){

        val createUsers=DataBase.createUser()
        registerUserModel.postValue(createUsers)
    }
}