package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.domain.ReserveUseCase

class StatsViewModel(
    private val reserveUseCase: ReserveUseCase
) : ViewModel() {
    suspend fun getDateOfReserves(nameSportCenter: String): Result<List<Reserve>> {
        return reserveUseCase.getListAdminNotificationReserve(nameSportCenter)
    }


}