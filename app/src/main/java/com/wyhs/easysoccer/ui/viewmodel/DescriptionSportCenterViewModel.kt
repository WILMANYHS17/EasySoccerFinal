package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.Comments
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.data.models.Users
import com.wyhs.easysoccer.domain.CommentsUseCase
import com.wyhs.easysoccer.domain.SportCenterUseCase
import com.wyhs.easysoccer.domain.UsersUseCase

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

    suspend fun generateRandomNumber(): Int {
        val generatedNumbers = mutableSetOf<Int>()
        var number: Int
        do {
            number = (100000..999999).random()
        } while (generatedNumbers.contains(number))
        generatedNumbers.add(number)
        return number
    }
}