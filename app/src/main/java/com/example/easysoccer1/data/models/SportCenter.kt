package com.example.easysoccer1.data.models

data class SportCenter(
    var nameSportCenter: String = "",
    var address: String = "",
    var nit: String = "",
    var price5vs5: Int = 0,
    var price8vs8: Int = 0,
    var description: String = "",
    val emailAdmin: String = "",
    val imageSportCenterUrl: String = "",
    val locationSportCenter: String = ""
)