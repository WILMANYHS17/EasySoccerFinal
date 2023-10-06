package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Reserve
import com.wyhs.easysoccer.domain.ReserveUseCase

class StatsViewModel(
    private val reserveUseCase: ReserveUseCase
) : ViewModel() {
    suspend fun getDateOfReserves(nameSportCenter: String): Result<List<Reserve>> {
        return reserveUseCase.getListAdminNotificationReserve(nameSportCenter)
    }


}