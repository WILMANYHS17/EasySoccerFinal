package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.SportCenter
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class SportCenterUseCaseTest {
    private var databaseUserRepository: DatabaseUserRepository = mockk(relaxed = true)
    private lateinit var sportCenterUseCase: SportCenterUseCase

    @Before
    fun setup() {
        sportCenterUseCase = SportCenterUseCase(databaseUserRepository)
    }

    @Test
    fun getSportCenter() {
        runBlocking {
            //given
            val sportCenter = SportCenter(
                nameSportCenter = "",
                address = "",
                nit = "a",
                price5vs5 = "",
                price8vs8 = "",
                description = "",
                emailAdmin = "a",
                imageSportCenterUrl = "",
                locationSportCenter = ""
            )
            coEvery { databaseUserRepository.getSportCenter("a", "a") } returns Result.success(
                sportCenter
            )
            //when
            val result = sportCenterUseCase.getSportCenter("a", "a").getOrNull()
            //then
            assertEquals(sportCenter.nameSportCenter, result?.nameSportCenter)

            coVerify(exactly = 1) { databaseUserRepository.getSportCenter("a", "a") }
        }
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

            coEvery { databaseUserRepository.getListSportCenter("a") } returns Result.success(
                sportCenterList
            )
            //when
            val result = sportCenterUseCase.getListSportCenter("a").getOrNull()
            //then
            assertEquals(sportCenterList.last(), result?.last())
            coVerify { databaseUserRepository.getListSportCenter("a") }
        }
    }

    @Test
    fun getSportCenterUser() {
        runBlocking {
            //given
            val sportCenter = SportCenter(
                nameSportCenter = "",
                address = "",
                nit = "a",
                price5vs5 = "",
                price8vs8 = "",
                description = "",
                emailAdmin = "a",
                imageSportCenterUrl = "",
                locationSportCenter = ""
            )
            coEvery { databaseUserRepository.getSportCenterUser("a") } returns Result.success(
                sportCenter
            )
            //when
            val result = sportCenterUseCase.getSportCenterUser("a").getOrNull()
            //then
            assertEquals(sportCenter.nit, result?.nit)

            coVerify(exactly = 1) { databaseUserRepository.getSportCenterUser("a") }
        }
    }

    @Test
    fun getImageSportCenter() {
        runBlocking {
            //given
            coEvery { databaseUserRepository.getImageSportCenter("a") } returns Result.success("a")
            //when
            val result = sportCenterUseCase.getImageSportCenter("a").getOrNull()
            //then
            assertEquals("a", result)
            coVerify { databaseUserRepository.getImageSportCenter("a") }
        }
    }

    @Test
    fun getListImageSportCenter() {
        runBlocking {
            //given
            val listImage = listOf("a", "a", "w")
            coEvery { databaseUserRepository.getListImageSportCenter("a") } returns Result.success(
                listImage
            )
            //when
            val result = sportCenterUseCase.getListImageSportCenter("a").getOrNull()
            //then
            assertEquals(listImage.last(), result?.last())
            coVerify { databaseUserRepository.getListImageSportCenter("a") }
        }
    }
    @Test
    fun getNitSportCenter() {
        runBlocking {
            //given
            val sportCenter = SportCenter(
                nameSportCenter = "",
                address = "",
                nit = "a",
                price5vs5 = "",
                price8vs8 = "",
                description = "",
                emailAdmin = "a",
                imageSportCenterUrl = "",
                locationSportCenter = ""
            )
            coEvery { databaseUserRepository.getNitSportCenter("a") } returns Result.success(
                sportCenter
            )
            //when
            val result = sportCenterUseCase.getNitSportCenter("a").getOrNull()
            //then
            assertEquals(sportCenter.nit, result?.nit)

            coVerify(exactly = 1) { databaseUserRepository.getNitSportCenter("a") }
        }
    }

}