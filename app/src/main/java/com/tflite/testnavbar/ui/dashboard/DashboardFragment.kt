package com.tflite.testnavbar.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.tflite.testnavbar.data.Result
import com.tflite.testnavbar.databinding.FragmentDashboardBinding
import com.tflite.testnavbar.viewmodel.WeatherViewModel
import com.tflite.testnavbar.viewmodel.WeatherViewModelFactory

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = WeatherViewModelFactory.getInstance()
        val viewModel: WeatherViewModel by viewModels { factory }

        viewModel.weatherData.observe(viewLifecycleOwner) { results ->
            when (results) {
                is Result.Loading -> { binding.progressBar.visibility = View.VISIBLE}
                is Result.Success -> {
                    val weather = results.data

                    binding.progressBar.visibility = View.GONE
                    binding.tvCity.text = weather.name
                    binding.tvCountry.text = weather.sys.country
                    binding.tvDescription.text = weather.weather[0].description
                    binding.tvHumidity.text = "Humidity: ${weather.main.humidity}%"
                    binding.tvWindSpeed.text = "Wind Speed: ${weather.wind.speed} m/s"
                    binding.tvTemperature.text = "Temperature: ${weather.main.temp}°C"
                }
                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
        viewModel.fetchWeather()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}