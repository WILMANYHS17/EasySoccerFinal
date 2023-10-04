package com.example.easysoccer1.data.models

import android.net.Uri

data class Users(
    val birthday: String = "",
    val password: String = "",
    var identification: String = "",
    val nameUser: String = "",
    val phone: String = "",
    val name: String = "",
    // Por si la variable booleana comienza en "is"
    @field:JvmField
    var isAdmin: Boolean = false,
    val email: String = "",
    val imageUserUrl: String = ""

)

