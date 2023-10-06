package com.wyhs.easysoccer.data.database

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.After
import org.junit.Before
import org.junit.Test

class DataBaseImplTest {

    // Mocks para FirebaseFirestore y FirebaseStorage
    @MockK
    private lateinit var mockDatabase: FirebaseFirestore

    @MockK
    private lateinit var mockStorage: FirebaseStorage

    // Clase bajo prueba
    private lateinit var databaseImpl: com.wyhs.easysoccer.data.database.DataBaseImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        databaseImpl = com.wyhs.easysoccer.data.database.DataBaseImpl(mockDatabase, mockStorage)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun testGetUser_Success() {
        val email = "test@example.com"
        val password = "password123"
        val snapshot = mockk<DocumentSnapshot>()

        // Configura el comportamiento simulado de los mocks
        coEvery { mockDatabase.collection("Users").document(email).get().await() } returns snapshot
        every { snapshot.get("password") } returns password

        // Ejecuta el método bajo prueba
        val result = runBlocking { databaseImpl.getUser(email, password) }

        // Verifica que el resultado sea el esperado
        assert(result.isSuccess)
        assert(result.getOrDefault(false))

        // Verifica que se llamó al método con los argumentos esperados
        coVerify { mockDatabase.collection("Users").document(email).get().await() }
    }

    @Test
    fun testGetUser_Failure() {
        val email = "test@example.com"
        val password = "password123"
        val incorrectPassword = "incorrect"

        val snapshot = mockk<DocumentSnapshot>()

        // Configura el comportamiento simulado de los mocks
        coEvery { mockDatabase.collection("Users").document(email).get().await() } returns snapshot
        every { snapshot.get("password") } returns incorrectPassword

        // Ejecuta el método bajo prueba
        val result = runBlocking { databaseImpl.getUser(email, password) }

        // Verifica que el resultado sea el esperado
        assert(result.isSuccess)
        assert(!result.getOrDefault(false))

        // Verifica que se llamó al método con los argumentos esperados
        coVerify { mockDatabase.collection("Users").document(email).get().await() }
    }
}