package com.example.easysoccer1.ui.viewmodel

import com.example.easysoccer1.data.models.SportCenter
import com.example.easysoccer1.domain.CommentsUseCase
import com.example.easysoccer1.domain.SportCenterUseCase
import com.example.easysoccer1.domain.UsersUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class RegisterSportCenterViewModelTest {
    private var sportCenterUseCase: SportCenterUseCase = mockk(relaxed = true)
    private lateinit var registerSportCenterViewModel: RegisterSportCenterViewModel

    @Before
    fun setup() {
        registerSportCenterViewModel =
            RegisterSportCenterViewModel(sportCenterUseCase)
    }

    @Test
    fun getImageSportCenter() {
        runBlocking {
            //given
            coEvery { sportCenterUseCase.getImageSportCenter("a") } returns Result.success("a")
            //when
            val result = registerSportCenterViewModel.getImageSportCenter("a").getOrNull()
            //then
            assertEquals("a", result)
            coVerify { sportCenterUseCase.getImageSportCenter("a") }
        }
    }

    @Test
    fun getListImageSportCenter() {
        runBlocking {
            //given
            val listImage = listOf("a", "a", "w")
            coEvery { sportCenterUseCase.getListImageSportCenter("a") } returns Result.success(
                listImage
            )
            //when
            val result = registerSportCenterViewModel.getListImageSportCenter("a").getOrNull()
            //then
            assertEquals(listImage.last(), result?.last())
            coVerify { sportCenterUseCase.getListImageSportCenter("a") }
        }
    }

    @Test
    fun getSportCenter() {
        runBlocking {
            //given
            val sportCenter = SportCenter(
                nameSportCenter = "Gym A",
                address = "",
                nit = "",
                price5vs5 = "",
                price8vs8 = "",
                description = "",
                emailAdmin = "",
                imageSportCenterUrl = "",
                locationSportCenter = ""
            )
            coEvery { sportCenterUseCase.getSportCenter("a","a") } returns Result.success(sportCenter)
            //when
            val result = registerSportCenterViewModel.getSportCenter("a","a").getOrNull()
            //then
            assertEquals(sportCenter.nameSportCenter, result?.nameSportCenter)

            coVerify(exactly = 1) { sportCenterUseCase.getSportCenter("a", "a") }
        }
    }

    @Test
    fun getNitSportCenter() {
        runBlocking {
            //given
            val sportCenter = SportCenter(
                nameSportCenter = "Gym A",
                address = "",
                nit = "",
                price5vs5 = "",
                price8vs8 = "",
                description = "",
                emailAdmin = "",
                imageSportCenterUrl = "",
                locationSportCenter = ""
            )
            coEvery { sportCenterUseCase.getNitSportCenter("a") } returns Result.success(sportCenter)
            //when
            val result = registerSportCenterViewModel.getNitSportCenter("a").getOrNull()
            //then
            assertEquals(sportCenter.nameSportCenter, result?.nameSportCenter)

            coVerify(exactly = 1) { sportCenterUseCase.getNitSportCenter("a") }
        }
    }
}