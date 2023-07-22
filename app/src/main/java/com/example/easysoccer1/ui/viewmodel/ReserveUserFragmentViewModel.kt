package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.domain.ReserveUseCase

class ReserveUserFragmentViewModel(
    private val reserveUseCase: ReserveUseCase
) : ViewModel() {
    suspend fun getListReserveUser(emailUser: String?): Result<List<Reserve>> {
        return reserveUseCase.getListReserveUser(emailUser)
    }

}