package com.wyhs.easysoccer.domain

import android.net.Uri
import com.wyhs.easysoccer.data.models.SportCenter

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

    suspend fun setImageSportCenter(nit: String, uriImageSportCenter: Uri) {
        databaseUserRepository.setImageSportCenter(nit, uriImageSportCenter)
    }

    suspend fun getImageSportCenter(nit: String): Result<String> {
        return databaseUserRepository.getImageSportCenter(nit)
    }

    fun setListImageSportCenter(uriList: MutableList<Uri>, nit: String) {
        databaseUserRepository.setListImageSportCenter(uriList, nit)
    }

    suspend fun getListImageSportCenter(nit: String): Result<List<String>> {
        return databaseUserRepository.getListImageSportCenter(nit)
    }

    suspend fun getNitSportCenter(nit: String): Result<SportCenter> {
        return databaseUserRepository.getNitSportCenter(nit)
    }

}