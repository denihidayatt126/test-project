package com.tflite.testnavbar.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tflite.testnavbar.data.Result
import com.tflite.testnavbar.data.remote.response.WeatherResponse
import com.tflite.testnavbar.data.remote.retrofit.ApiService

class WeatherRepository(private val apiService: ApiService) {
    private val _weatherData = MutableLiveData<Result<WeatherResponse>>()
    val weatherData: LiveData<Result<WeatherResponse>> = _weatherData

    suspend fun getWeather() {
        _weatherData.postValue(Result.Loading)
        try {
            val response = apiService.getWeather()
            _weatherData.postValue(Result.Success(response))
        } catch (e: Exception) {
            _weatherData.postValue(Result.Error("Error: ${e.message}"))
        }
    }
}