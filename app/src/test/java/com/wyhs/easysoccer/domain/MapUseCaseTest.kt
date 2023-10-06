package com.wyhs.easysoccer.domain

import io.mockk.mockk
import org.junit.Before

class MapUseCaseTest {

    private var mapRepository: MapRepository = mockk(relaxed = true)
    private lateinit var mapUseCase: MapUseCase

    @Before
    fun setup(){
        mapUseCase = MapUseCase(mapRepository)
    }


}