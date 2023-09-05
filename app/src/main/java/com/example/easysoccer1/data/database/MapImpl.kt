package com.example.easysoccer1.data.database

import com.example.easysoccer1.data.models.RouteResponse
import com.example.easysoccer1.domain.ApiService
import com.example.easysoccer1.domain.MapRepository
import retrofit2.Response
import retrofit2.Retrofit

class MapImpl(
    val apiService: ApiService
) : MapRepository {
    override suspend fun getRoute(token: String, start: String, end: String): Response<RouteResponse>{
        return apiService.getRoute(token, start, end)
    }

}