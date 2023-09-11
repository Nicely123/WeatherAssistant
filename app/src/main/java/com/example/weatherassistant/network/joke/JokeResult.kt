package com.example.weatherassistant.network.joke

import com.squareup.moshi.Json

data class JokeResult(
    @Json(name = "error_code")
    val errorCode: Int,
    val reason: String,
    @Json(name = "result")
    val networkJoke: List<NetworkJoke>
)

