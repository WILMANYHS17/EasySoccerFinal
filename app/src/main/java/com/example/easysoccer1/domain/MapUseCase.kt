package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.RouteResponse
import retrofit2.Response


class MapUseCase(
    private val mapRepository: MapRepository
) {
    suspend fun getRoute(token: String, start: String, end: String): Response<RouteResponse> {
        return mapRepository.getRoute(token,start, end)
    }
}