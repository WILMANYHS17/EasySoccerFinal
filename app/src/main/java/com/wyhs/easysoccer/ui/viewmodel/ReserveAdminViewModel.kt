package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Reserve
import com.wyhs.easysoccer.domain.ReserveUseCase

class ReserveAdminViewModel(
    private val reserveUseCase: ReserveUseCase
) : ViewModel() {
    suspend fun getListAdminNotificationReserve(nameSportCenter: String): Result<List<Reserve>> {
        return reserveUseCase.getListAdminNotificationReserve(nameSportCenter)
    }


}