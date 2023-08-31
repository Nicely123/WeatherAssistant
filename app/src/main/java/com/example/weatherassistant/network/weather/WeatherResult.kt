package com.example.weatherassistant.network.weather

import com.squareup.moshi.Json

data class WeatherResult(
    @Json(name = "error_code")
    val errorCode: Int,
    val reason: String,
    @Json(name = "result")
    val weather: Weather
)