package com.tflite.testnavbar.di

import com.tflite.testnavbar.data.remote.WeatherRepository
import com.tflite.testnavbar.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(): WeatherRepository {
        val apiService = ApiConfig.getApiService()
        return WeatherRepository(apiService)
    }
}