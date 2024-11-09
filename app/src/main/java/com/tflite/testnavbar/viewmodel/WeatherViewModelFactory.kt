package com.tflite.testnavbar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tflite.testnavbar.data.remote.WeatherRepository
import com.tflite.testnavbar.di.Injection

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory private constructor(private val repository: WeatherRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: WeatherViewModelFactory? = null
        fun getInstance(): WeatherViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: WeatherViewModelFactory(Injection.provideRepository())
            }.also { instance = it }
    }
}