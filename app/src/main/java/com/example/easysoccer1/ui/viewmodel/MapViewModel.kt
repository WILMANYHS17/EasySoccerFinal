package com.example.easysoccer1.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.easysoccer1.data.models.RouteResponse
import com.example.easysoccer1.domain.MapUseCase
import retrofit2.Response

class MapViewModel (
    private val mapUseCase: MapUseCase
): ViewModel(){
    suspend fun getRoute(token: String, start: String, end: String): Response<RouteResponse> {
       return mapUseCase.getRoute(token, start, end)
    }
}