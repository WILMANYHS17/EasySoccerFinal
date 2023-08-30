package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.Comments
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.data.models.Users
import com.example.easysoccer1.domain.CommentsUseCase
import com.example.easysoccer1.domain.SportCenterUseCase
import com.example.easysoccer1.domain.UsersUseCase

class DescriptionSportCenterViewModel(
    val sportCenterUseCase: SportCenterUseCase,
    val usersUseCase: UsersUseCase,
    val commentsUseCase: CommentsUseCase

) : ViewModel() {
    suspend fun getSportCenterUser(nit: String?): Result<SportCenter> {
        return sportCenterUseCase.getSportCenterUser(nit)
    }

    suspend fun getUser(email: String): Result<Users> {
        return usersUseCase.searchUser(email)
    }

    fun setComment(comment: Comments) {
        commentsUseCase.setComment(comment)
    }

    suspend fun getListImageSportCenter(nit: String): Result<List<String>> {
        return sportCenterUseCase.getListImageSportCenter(nit)
    }
}