package com.example.weatherassistant.network.weather

data class Weather(
    val city: String,
    val future: List<Future>,
    val realtime: Realtime
)