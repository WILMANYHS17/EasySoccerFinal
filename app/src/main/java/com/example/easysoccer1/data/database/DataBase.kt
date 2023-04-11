package com.example.easysoccer1.data.database

import com.example.easysoccer1.data.models.RegisterUsers
import com.google.firebase.firestore.FirebaseFirestore

class DataBase {


    companion object{
        private val dataBase = FirebaseFirestore.getInstance()
        private val registerUsers: RegisterUsers = TODO()
        fun createUser() {
            val name= registerUsers.name
            val phone = registerUsers.phone
            val email = registerUsers.email
            val nameUser = registerUsers.nameUser
            val password= registerUsers.password
            val birthday = registerUsers.birthday
            val isAdmin = registerUsers.isAdmin

            email?.let {
                dataBase.collection("Users").document(email.toString()).set(
                    hashMapOf("name" to name,
                        "phone" to phone,
                        "email" to email,
                        "nameUser" to nameUser,
                        "password" to password,
                        "birthday" to birthday,
                        "isAdmin" to (isAdmin=="Admin"))
                )

            }

        }

    }

}