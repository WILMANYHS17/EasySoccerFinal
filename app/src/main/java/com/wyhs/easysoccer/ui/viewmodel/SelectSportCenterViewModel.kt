package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.SportCenterUseCase

class SelectSportCenterViewModel(
    private val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {

    suspend fun getListSportCenter(email: String?): Result<List<SportCenter>> {
        return sportCenterUseCase.getListSportCenter(email)
    }
}