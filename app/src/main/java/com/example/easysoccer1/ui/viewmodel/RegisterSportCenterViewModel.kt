package com.example.easysoccer1.ui.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.SportCenterUseCase

class RegisterSportCenterViewModel(
    private val sportCenterUseCase: SportCenterUseCase
) : ViewModel() {
    fun setSportCenter(sportCenter: SportCenter) {
        sportCenterUseCase.setSportCenter(sportCenter)
    }

    fun setImageSportCenter(nit: String, uriImageSportCenter: Uri) {
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

}