package com.tflite.testnavbar.data.remote.retrofit

import com.tflite.testnavbar.data.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String = "Jakarta",
        @Query("appid") apiKey: String = "81755ae6c9198d678de929351b503a1c",
        @Query("units") units: String = "metric"
    ): WeatherResponse
}