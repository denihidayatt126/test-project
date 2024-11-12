package com.tflite.testnavbar.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.tflite.testnavbar.R
import com.tflite.testnavbar.data.Result
import com.tflite.testnavbar.databinding.FragmentHomeBinding
import com.tflite.testnavbar.viewmodel.WeatherViewModel
import com.tflite.testnavbar.viewmodel.WeatherViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val factory = WeatherViewModelFactory.getInstance()
        val viewModel: WeatherViewModel by viewModels { factory }

        viewModel.weatherData.observe(viewLifecycleOwner) { results->
            when (results) {
                is Result.Loading -> { binding.progressIndicator.visibility = View.VISIBLE}
                is Result.Success -> {
                    val weather = results.data

                    binding.progressIndicator.visibility = View.GONE
                    binding.tvCity.text = getString(R.string.city_country_info, weather.name, weather.sys.country)
                    binding.tvInfoDescriptionWeather.text = weather.weather[0].description
                    binding.tvInfoHumidityPercentage.text = getString(R.string.humidity_info, weather.main.humidity)
                    binding.tvInfoWindSpeed.text = getString(R.string.wind_speed_info, weather.wind.speed)
                    binding.tvInfoTemperature.text = getString(R.string.temperature_info, weather.main.temp)
                    binding.tvInfoPressure.text = getString(R.string.pressure_info, weather.main.pressure)
                }
                is Result.Error -> {
                    binding.progressIndicator.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}