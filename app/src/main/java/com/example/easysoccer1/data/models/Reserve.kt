package com.example.easysoccer1.data.models

data class Reserve(
    val numberReserve: Int = 0,
    val nameSportCenter: String = "",
    val nameReserveBy: String = "",
    val date: String = "",
    val hour: String = "",
    val price: Int = 0,
    val paidOrNot: Boolean = false,
    val address: String = "",
    val numberPlayers: String = "",
    val numberGoal: Int = 0
)