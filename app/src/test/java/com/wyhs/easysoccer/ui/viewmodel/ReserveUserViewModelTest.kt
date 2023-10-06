package com.wyhs.easysoccer.ui.viewmodel

import com.wyhs.easysoccer.data.models.Goals
import com.wyhs.easysoccer.data.models.SportCenter
import com.wyhs.easysoccer.domain.GoalsUseCase
import com.wyhs.easysoccer.domain.ReserveUseCase
import com.wyhs.easysoccer.domain.SportCenterUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class ReserveUserViewModelTest {
    private val reserveUseCase: ReserveUseCase = mockk(relaxed = true)
    private val sportCenterUseCase: SportCenterUseCase = mockk(relaxed = true)
    private val goalsUseCase: GoalsUseCase = mockk(relaxed = true)
    private lateinit var reserveUserViewModel: ReserveUserViewModel

    @Before
    fun setup() {
        reserveUserViewModel = ReserveUserViewModel(reserveUseCase, sportCenterUseCase, goalsUseCase)
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
            coEvery { sportCenterUseCase.getSportCenterUser("a") } returns Result.success(sportCenter)
            //when
            val result = reserveUserViewModel.getSportCenter("a").getOrNull()
            //then
            assertEquals(sportCenter.nameSportCenter, result?.nameSportCenter)

            coVerify(exactly = 1) { sportCenterUseCase.getSportCenterUser("a") }
        }
    }

    @Test
    fun getGoal() {
        runBlocking {
            //given
            val goal = Goals(
                number = "1",
                size = "",
                price = "",
                available = "",
                hour = "",
                date = ""
            )
            coEvery { goalsUseCase.getGoal("a", "a") } returns Result.success(goal)
            //when
            val result = reserveUserViewModel.getGoal("a", "a").getOrNull()
            //then
            assertEquals(goal.number, result?.number)

            coVerify (exactly = 1){goalsUseCase.getGoal("a", "a")}
        }
    }
}