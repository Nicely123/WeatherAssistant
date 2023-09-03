package com.example.weatherassistant.network.city

import com.squareup.moshi.Json

data class CityResult(
    @Json(name = "error_code")
    val errorCode: Int,
    val reason: String,
    @Json(name = "result")
    val netWorkCity: List<NetWorkCity>
)