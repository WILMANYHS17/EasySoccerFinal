package com.example.easysoccer1.data.database

import com.example.easysoccer1.data.models.RegisterUsers
import com.example.easysoccer1.domain.DatabaseUserRepository
import com.google.firebase.firestore.FirebaseFirestore

class DataBaseImpl(
    private val dataBase: FirebaseFirestore
) : DatabaseUserRepository {

    override fun createUser(registerUsers: RegisterUsers) {
        dataBase.collection("Users").document(registerUsers.email).set(
            hashMapOf(
                "name" to registerUsers.name,
                "phone" to registerUsers.phone,
                "email" to registerUsers.email,
                "nameUser" to registerUsers.nameUser,
                "password" to registerUsers.password,
                "birthday" to registerUsers.birthday,
                "isAdmin" to (registerUsers.isAdmin == "Admin")
            )
        )
    }

    override fun getUser(id: String): RegisterUsers {
       return TODO()
    }

}