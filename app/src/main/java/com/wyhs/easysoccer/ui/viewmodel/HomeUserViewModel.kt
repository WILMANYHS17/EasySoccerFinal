package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.UsersUseCase

class HomeUserViewModel(
    val usersUseCase: UsersUseCase
) : ViewModel() {
    suspend fun getListSportsCenter(
        date: String,
        hour: String,
        optionsFinal: String
    ): Result<List<SportCenter>> {
        return usersUseCase.getListSportsCenter(date, hour, optionsFinal)
    }
}