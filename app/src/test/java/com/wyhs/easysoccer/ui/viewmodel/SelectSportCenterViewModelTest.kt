package com.wyhs.easysoccer.ui.viewmodel

import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.SportCenterUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class SelectSportCenterViewModelTest {
    private val sportCenterUseCase: SportCenterUseCase = mockk(relaxed = true)
    private lateinit var selectSportCenterViewModel: SelectSportCenterViewModel

    @Before
    fun setup() {
        selectSportCenterViewModel = SelectSportCenterViewModel(sportCenterUseCase)
    }

    @Test
    fun getListSportCenter() {
        runBlocking {
            //given
            val sportCenterList = listOf(
                SportCenter(
                    "Gym A",
                    address = "",
                    nit = "",
                    price5vs5 = "",
                    price8vs8 = "",
                    description = "",
                    emailAdmin = "",
                    imageSportCenterUrl = "",
                    locationSportCenter = ""
                ),
                SportCenter(
                    "Gym B",
                    address = "",
                    nit = "",
                    price5vs5 = "",
                    price8vs8 = "",
                    description = "",
                    emailAdmin = "",
                    imageSportCenterUrl = "",
                    locationSportCenter = ""
                )
            )

            coEvery { sportCenterUseCase.getListSportCenter("a") } returns Result.success(
                sportCenterList
            )
            //when
            val result = selectSportCenterViewModel.getListSportCenter("a").getOrNull()
            //then
            assertEquals(sportCenterList.last(), result?.last())
            coVerify { sportCenterUseCase.getListSportCenter("a") }
        }
    }
}