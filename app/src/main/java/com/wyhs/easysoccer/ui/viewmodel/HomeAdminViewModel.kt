package com.wyhs.easysoccer.ui.viewmodel


import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Comments
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.CommentsUseCase
import com.wyhs.easysoccer.domain.SportCenterUseCase

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

    suspend fun getListImageSportCenter(nit: String): Result<List<String>> {
        return sportCenterUseCase.getListImageSportCenter(nit)
    }

}

