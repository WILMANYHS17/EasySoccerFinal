package com.wyhs.easysoccer.data.database

import com.wyhs.easysoccer.data.models.RouteResponse
import com.wyhs.easysoccer.domain.ApiService
import com.wyhs.easysoccer.domain.MapRepository
import retrofit2.Response

class MapImpl(
    val apiService: ApiService
) : MapRepository {
    override suspend fun getRoute(token: String, start: String, end: String): Response<RouteResponse>{
        return apiService.getRoute(token, start, end)
    }

}