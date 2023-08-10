package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.Comments

class CommentsUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    fun setComment(comment: Comments) {
        databaseUserRepository.setComment(comment)
    }
}