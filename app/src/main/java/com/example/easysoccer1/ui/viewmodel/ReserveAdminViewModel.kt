package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.domain.ReserveUseCase

class ReserveAdminViewModel(
    private val reserveUseCase: ReserveUseCase
) : ViewModel() {
    suspend fun getListAdminNotificationReserve(nameSportCenter: String): Result<List<Reserve>> {
        return reserveUseCase.getListAdminNotificationReserve(nameSportCenter)
    }


}