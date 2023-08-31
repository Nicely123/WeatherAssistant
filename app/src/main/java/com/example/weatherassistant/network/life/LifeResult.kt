package com.example.weatherassistant.network.life

import com.squareup.moshi.Json

data class LifeResult(
    @Json(name = "error_code")
    val errorCode: Int,
    val reason: String,
    @Json(name = "result")
    val result: Result
)