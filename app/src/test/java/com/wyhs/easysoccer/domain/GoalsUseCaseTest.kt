package com.wyhs.easysoccer.domain

import com.wyhs.easysoccer.data.models.Goals
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*



class GoalsUseCaseTest {
    private var databaseUserRepository: DatabaseUserRepository = mockk(relaxed = true)
    private lateinit var goalsUseCase: GoalsUseCase

    @Before
    fun setup() {
        goalsUseCase = GoalsUseCase(databaseUserRepository)
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

            coEvery { databaseUserRepository.getListGoals("a", "a") } returns Result.success(
                goalList
            )
            //when
            val result = goalsUseCase.getListGoals("a", "a").getOrNull()
            //then
            assertEquals(goalList.last(), result?.last())
            coVerify { databaseUserRepository.getListGoals("a", "a") }
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
            coEvery { databaseUserRepository.getGoal("a", "a") } returns Result.success(goal)
            //when
            val result = goalsUseCase.getGoal("a", "a").getOrNull()
            //then
            assertEquals(goal.number, result?.number)

            coVerify (exactly = 1){databaseUserRepository.getGoal("a", "a")}
        }
    }
}