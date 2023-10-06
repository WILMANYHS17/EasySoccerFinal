package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Reserve
import com.wyhs.easysoccer.domain.ReserveUseCase

class ReserveUserFragmentViewModel(
    private val reserveUseCase: ReserveUseCase
) : ViewModel() {
    suspend fun getListReserveUser(emailUser: String?): Result<List<Reserve>> {
        return reserveUseCase.getListReserveUser(emailUser)
    }

    suspend fun cancelReserve(number: String, emailUser: String?) {
        reserveUseCase.cancelReserve(number, emailUser)
    }

}