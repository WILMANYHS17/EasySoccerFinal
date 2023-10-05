package com.example.easysoccer1.domain

import com.example.easysoccer1.data.models.Comments
import com.example.easysoccer1.data.models.Goals
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*


class CommentsUseCaseTest {

    private var databaseUserRepository: DatabaseUserRepository = mockk(relaxed = true)
    private lateinit var commentsUseCase: CommentsUseCase

    @Before
    fun setup() {
        commentsUseCase = CommentsUseCase(databaseUserRepository)
    }

    @Test
    fun getListComments() {
        runBlocking {
            //given
            val commentList = listOf(
                Comments(
                    emailUser = "",
                    nameUser = "",
                    nameSportCenter = "",
                    comment = "",
                    id = "1",
                ),
                Comments(
                    emailUser = "",
                    nameUser = "",
                    nameSportCenter = "",
                    comment = "",
                    id = "2",
                )
            )

            coEvery { databaseUserRepository.getListComments("a") } returns Result.success(
                commentList
            )
            //when
            val result = commentsUseCase.getListComments("a").getOrNull()
            //then
            assertEquals(commentList.last(), result?.last())
            coVerify { databaseUserRepository.getListComments("a") }
        }
    }
}