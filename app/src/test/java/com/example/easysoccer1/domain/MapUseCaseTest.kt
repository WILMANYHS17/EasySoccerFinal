package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.RouteResponse
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import retrofit2.Response

class MapUseCaseTest {

    private var mapRepository: MapRepository = mockk(relaxed = true)
    private lateinit var mapUseCase: MapUseCase

    @Before
    fun setup(){
        mapUseCase = MapUseCase(mapRepository)
    }


}