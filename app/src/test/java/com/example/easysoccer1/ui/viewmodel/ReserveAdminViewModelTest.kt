package com.example.easysoccer1.ui.viewmodel

import com.example.easysoccer1.data.models.Reserve
import com.example.easysoccer1.domain.ReserveUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test

class ReserveAdminViewModelTest {
    private val reserveUseCase: ReserveUseCase = mockk(relaxed = true)
    private lateinit var reserveAdminViewModel: ReserveAdminViewModel
    @Before
    fun setup() {
        reserveAdminViewModel =
            ReserveAdminViewModel(reserveUseCase)
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
            coEvery { reserveUseCase.getListAdminNotificationReserve("a") } returns Result.success(
                reserveList
            )
            //when
            val result = reserveAdminViewModel.getListAdminNotificationReserve("a").getOrNull()
            //then
            assertEquals(reserveList.last(), result?.last())

            coVerify(exactly = 1) { reserveUseCase.getListAdminNotificationReserve("a") }
        }
    }
}