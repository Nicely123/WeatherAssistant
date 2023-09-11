package com.example.weatherassistant.network

import com.example.weatherassistant.network.interceptor.ApiKeyInterceptor
import com.example.weatherassistant.network.joke.JokeResult
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface JokeApiService {
    @GET("randJoke.php")
    suspend fun getJoke(): JokeResult
}

object JokeApi{
    private const val API_KEY = "a33e8d49365993ff5dc23e5a6832210e"
    // https://v.juhe.cn/joke/randJoke.php
    private const val BASE_URL = "https://v.juhe.cn/joke/"
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(ApiKeyInterceptor(API_KEY))
        .build()
    val retrofitService: JokeApiService by lazy {
        val newRetrofit = retrofit.newBuilder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .baseUrl(BASE_URL)
            .build()
        newRetrofit.create(JokeApiService::class.java)
    }
}
