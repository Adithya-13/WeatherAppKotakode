package com.codext.project.weatherappkotakode

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.codext.project.weatherappkotakode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getForecast()
        mainViewModel.weatherList.observe(this, { weather ->
            binding.viewPagerSlider.adapter = WeatherAdapter(weather.city, weather.list)
        })

        mainViewModel.isLoading.observe(this, { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

}