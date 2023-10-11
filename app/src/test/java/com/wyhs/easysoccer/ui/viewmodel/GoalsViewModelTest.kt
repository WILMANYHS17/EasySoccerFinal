package com.wyhs.easysoccer.ui.viewmodel

import com.wyhs.easysoccer.data.models.Goals
import com.wyhs.easysoccer.domain.GoalsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class GoalsViewModelTest {
    private val goalsUseCase: GoalsUseCase = mockk(relaxed = true)
    private lateinit var goalsViewModel: GoalsViewModel
    @Before
    fun setup() {
        goalsViewModel =
            GoalsViewModel(goalsUseCase)
    }
    @Test
    fun getListGoals() {
        runBlocking {
            //given
            val goalList = listOf(
                Goals(
                    number = "1",
                    size = "",
                    price = "",
                    available = "",
                    hour = "",
                    date = ""
                ),
                Goals(
                    number = "2",
                    size = "",
                    price = "",
                    available = "",
                    hour = "",
                    date = ""
                )
            )
            coEvery { goalsUseCase.getListGoals("a", "a") } returns Result.success(
                goalList
            )
            //when
            val result = goalsViewModel.getListGoals("a", "a").getOrNull()
            //then
            assertEquals(goalList.last(), result?.last())
            coVerify { goalsUseCase.getListGoals("a", "a") }
        }
    }
}