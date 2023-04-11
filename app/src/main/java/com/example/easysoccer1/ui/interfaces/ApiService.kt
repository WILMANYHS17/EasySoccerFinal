package com.example.easysoccer1.ui.interfaces

import com.example.easysoccer1.data.models.RouteResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/directions/driving-car")
    suspend fun getRoute(
        @Query("api_key") key: String,
        @Query("start", encoded = true) start: String,
        @Query("end", encoded = true) end: String
    ):Response<RouteResponse>
}