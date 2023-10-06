package com.wyhs.easysoccer.domain

import com.wyhs.easysoccer.data.models.Comments

class CommentsUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    fun setComment(comment: Comments) {
        databaseUserRepository.setComment(comment)
    }

    suspend fun getListComments(nameSportCenter: String): Result<List<Comments>> {
        return databaseUserRepository.getListComments(nameSportCenter)
    }
}