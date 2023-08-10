package com.example.easysoccer1.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Comments
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.CommentsUseCase
import com.example.easysoccer1.domain.SportCenterUseCase

class HomeAdminViewModel(
    private val sportCenterUseCase: SportCenterUseCase,
    private val commentsUseCase: CommentsUseCase
) : ViewModel() {
    suspend fun getSportCenter(nit: String, email: String): Result<SportCenter> {
        return sportCenterUseCase.getSportCenter(nit, email)
    }

    suspend fun getListComments(nameSportCenter: String): Result<List<Comments>> {
        return commentsUseCase.getListComments(nameSportCenter)
    }

}

