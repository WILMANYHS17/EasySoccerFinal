package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.UsersUseCase

class HomeUserViewModel(
    val usersUseCase: UsersUseCase
) : ViewModel() {
    suspend fun getListSportsCenter(): Result<List<SportCenter>> {
        return usersUseCase.getListSportsCenter()
    }
}