package com.example.easysoccer1.domain

import android.net.Uri
import com.example.easysoccer1.data.models.SportCenter

class SportCenterUseCase(
    private val databaseUserRepository: DatabaseUserRepository
) {
    suspend fun getSportCenter(nit: String, email: String): Result<SportCenter> {
        return databaseUserRepository.getSportCenter(nit, email)
    }

    fun setSportCenter(sportCenter: SportCenter) {
        databaseUserRepository.createSportCenter(sportCenter)
    }

    suspend fun getListSportCenter(email: String?): Result<List<SportCenter>> {
        return databaseUserRepository.getListSportCenter(email)
    }

    suspend fun getSportCenterUser(nit: String?): Result<SportCenter> {
        return databaseUserRepository.getSportCenterUser(nit)
    }

    fun setImageSportCenter(nit: String, uriImageSportCenter: Uri) {
        databaseUserRepository.setImageSportCenter(nit, uriImageSportCenter)
    }

    suspend fun getImageSportCenter(nit: String): Result<String> {
        return databaseUserRepository.getImageSportCenter(nit)
    }

}