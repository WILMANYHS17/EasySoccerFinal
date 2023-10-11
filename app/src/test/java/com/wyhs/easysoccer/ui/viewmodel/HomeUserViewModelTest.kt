package com.wyhs.easysoccer.ui.viewmodel

import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.UsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class HomeUserViewModelTest {
    private val usersUseCase: UsersUseCase = mockk(relaxed = true)
    private lateinit var homeUserViewModel: HomeUserViewModel
    @Before
    fun setup() {
        homeUserViewModel =
            HomeUserViewModel(usersUseCase)
    }
    @Test
    fun getListSportsCenter() {
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
            coEvery { usersUseCase.getListSportsCenter("a", "a", "a") } returns Result.success(
                sportCenterList
            )
            //when
            val result = homeUserViewModel.getListSportsCenter("a", "a", "a").getOrNull()
            //then
            assertEquals(sportCenterList.last(), result?.last())
            coVerify { usersUseCase.getListSportsCenter("a", "a", "a") }
        }
    }
}