package com.tflite.testnavbar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tflite.testnavbar.data.remote.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    val weatherData = repository.weatherData

    fun fetchWeather() {
        viewModelScope.launch {
            repository.getWeather()
        }
    }
}