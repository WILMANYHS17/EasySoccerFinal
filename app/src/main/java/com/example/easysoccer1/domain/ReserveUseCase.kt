package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.Reserve

class ReserveUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {

    fun setReserve(reserve: Reserve, emailUser: String?) {
        databaseUserRepository.setReserve(reserve, emailUser)
    }

    suspend fun getListReserveUser(emailUser: String?): Result<List<Reserve>> {
        return databaseUserRepository.getListReserveUser(emailUser)
    }

    suspend fun getListAdminNotificationReserve(nameSportCenter: String): Result<List<Reserve>> {
        return databaseUserRepository.getListReserveAdmin(nameSportCenter)
    }

    suspend fun cancelReserve(number: String, emailUser: String?) {
        databaseUserRepository.cancelReserve(number,emailUser)
    }
}