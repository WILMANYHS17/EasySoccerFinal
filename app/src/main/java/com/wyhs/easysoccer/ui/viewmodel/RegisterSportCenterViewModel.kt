package com.wyhs.easysoccer.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.SportCenterUseCase

class RegisterSportCenterViewModel(
    private val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {
    fun setSportCenter(sportCenter: SportCenter) {
        sportCenterUseCase.setSportCenter(sportCenter)
    }

    suspend fun setImageSportCenter(nit: String, uriImageSportCenter: Uri) {
        sportCenterUseCase.setImageSportCenter(nit, uriImageSportCenter)
    }

    suspend fun getImageSportCenter(nit: String): Result<String> {
        return sportCenterUseCase.getImageSportCenter(nit)
    }

    fun setListImageSportCenter(uriList: MutableList<Uri>, nit: String) {
        sportCenterUseCase.setListImageSportCenter(uriList, nit)
    }

    suspend fun getListImageSportCenter(nit: String): Result<List<String>> {
        return sportCenterUseCase.getListImageSportCenter(nit)
    }

    suspend fun getSportCenter(nit: String, emailAdmin: String): Result<SportCenter> {
        return sportCenterUseCase.getSportCenter(nit, emailAdmin)
    }

    suspend fun getNitSportCenter(nit: String): Result<SportCenter> {
        return sportCenterUseCase.getNitSportCenter(nit)
    }

}