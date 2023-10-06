package com.wyhs.easysoccer.domain

import com.wyhs.easysoccer.data.models.RouteResponse
import retrofit2.Response


class MapUseCase(
    private val mapRepository: MapRepository
) {
    suspend fun getRoute(token: String, start: String, end: String): Response<RouteResponse> {
        return mapRepository.getRoute(token,start, end)
    }
}