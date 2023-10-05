package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.Reserve
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions

class ReserveUseCaseTest {
    private var databaseUserRepository: DatabaseUserRepository = mockk(relaxed = true)
    private lateinit var reserveUseCase: ReserveUseCase

    @Before
    fun setup() {
        reserveUseCase = ReserveUseCase(databaseUserRepository)
    }

    @Test
    fun getListReserveUser() {
        runBlocking {
            //given
            val reserveList = listOf(
                Reserve(
                    numberReserve = "2",
                    nameSportCenter = "",
                    nameReserveBy = "",
                    date = "",
                    hour = "",
                    price = "",
                    paidOrNot = "",
                    address = "",
                    numberPlayers = "",
                    numberGoal = ""
                ),
                Reserve(
                    numberReserve = "1",
                    nameSportCenter = "",
                    nameReserveBy = "",
                    date = "",
                    hour = "",
                    price = "",
                    paidOrNot = "",
                    address = "",
                    numberPlayers = "",
                    numberGoal = ""
                )
            )
            coEvery { databaseUserRepository.getListReserveUser("a") } returns Result.success(
                reserveList
            )
            //when
            val result = reserveUseCase.getListReserveUser("a").getOrNull()
            //then
            Assertions.assertEquals(reserveList.last(), result?.last())

            coVerify(exactly = 1) { databaseUserRepository.getListReserveUser("a") }
        }
    }

    @Test
    fun getListAdminNotificationReserve() {
        runBlocking {
            //given
            val reserveList = listOf(
                Reserve(
                    numberReserve = "2",
                    nameSportCenter = "",
                    nameReserveBy = "",
                    date = "",
                    hour = "",
                    price = "",
                    paidOrNot = "",
                    address = "",
                    numberPlayers = "",
                    numberGoal = ""
                ),
                Reserve(
                    numberReserve = "1",
                    nameSportCenter = "",
                    nameReserveBy = "",
                    date = "",
                    hour = "",
                    price = "",
                    paidOrNot = "",
                    address = "",
                    numberPlayers = "",
                    numberGoal = ""
                )
            )
            coEvery { databaseUserRepository.getListReserveAdmin("a") } returns Result.success(
                reserveList
            )
            //when
            val result = reserveUseCase.getListAdminNotificationReserve("a").getOrNull()
            //then
            Assertions.assertEquals(reserveList.last(), result?.last())

            coVerify(exactly = 1) { databaseUserRepository.getListReserveAdmin("a") }
        }
    }
}