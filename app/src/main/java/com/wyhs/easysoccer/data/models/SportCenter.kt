package com.wyhs.easysoccer.data.models

data class SportCenter(
    var nameSportCenter: String = "",
    var address: String = "",
    var nit: String = "",
    var price5vs5: String = "",
    var price8vs8: String = "",
    var description: String = "",
    val emailAdmin: String = "",
    val imageSportCenterUrl: String = "",
    val locationSportCenter: String = ""
)