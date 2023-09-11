package com.example.weatherassistant.domain

data class DisplayedCity(
    val id: String,
    val province: String,
    val city: String,
    val district: String
)

data class DisplayJoke(
    val content: String,
    val hashId: String,
    val unixtime: String    // 时间戳
)
