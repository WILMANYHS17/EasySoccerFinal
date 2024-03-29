package com.wyhs.easysoccer.ui.viewmodel

import com.wyhs.easysoccer.data.models.Reserve
import com.wyhs.easysoccer.domain.ReserveUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class ReserveUserFragmentViewModelTest {
    private val reserveUseCase: ReserveUseCase = mockk(relaxed = true)
    private lateinit var reserveUserFragmentViewModel: ReserveUserFragmentViewModel

    @Before
    fun setup() {
        reserveUserFragmentViewModel = ReserveUserFragmentViewModel(reserveUseCase)
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
            coEvery { reserveUseCase.getListReserveUser("a") } returns Result.success(
                reserveList
            )
            //when
            val result = reserveUserFragmentViewModel.getListReserveUser("a").getOrNull()
            //then
            assertEquals(reserveList.last(), result?.last())

            coVerify(exactly = 1) { reserveUseCase.getListReserveUser("a") }
        }
    }
}