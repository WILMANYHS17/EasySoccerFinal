package com.wyhs.easysoccer.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.wyhs.easysoccer.data.models.RouteResponse
import com.wyhs.easysoccer.domain.MapUseCase
import retrofit2.Response

class MapViewModel (
    private val mapUseCase: MapUseCase
): ViewModel(){
    suspend fun getRoute(token: String, start: String, end: String): Response<RouteResponse> {
       return mapUseCase.getRoute(token, start, end)
    }
}