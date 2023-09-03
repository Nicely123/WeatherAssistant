package com.example.weatherassistant.network

import com.example.weatherassistant.network.city.CityResult
import com.example.weatherassistant.network.interceptor.ApiKeyInterceptor
import com.example.weatherassistant.network.life.LifeResult
import com.example.weatherassistant.network.weather.WeatherResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("query")
    suspend fun getWeather(@Query("city") city: String): WeatherResult

    @GET("life")
    suspend fun getLife(@Query("city") city: String): LifeResult

    @GET("cityList")
    suspend fun getCityList(): CityResult
}

private const val BASE_URL = "https://apis.juhe.cn/simpleWeather/"
private const val API_KEY = "0380213d2fbcb033f4822999c3322876"

private val httpClient = OkHttpClient.Builder()
    .addInterceptor(ApiKeyInterceptor(API_KEY))
    .build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(httpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

object WeatherApi{
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}