package com.codext.project.weatherappkotakode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codext.project.weatherappkotakode.databinding.WeatherItemBinding
import com.github.islamkhsh.CardSliderAdapter

class WeatherAdapter(private val city: City, private val listData : List<ListItem>) : CardSliderAdapter<WeatherAdapter.WeatherViewHolder>() {

    override fun bindVH(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.WeatherViewHolder {
        val weatherItemBinding =
            WeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(weatherItemBinding)
    }

    inner class WeatherViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: ListItem) {
            val temp = "${weather.main.temp.toInt()}°c"
            val minMaxTemp = "${weather.main.tempMin}°c / ${weather.main.tempMax}°c"
            val placeWeather = "${city?.name}, ${city?.country}"
            with(binding) {
                temperature.text = temp
                minMaxWeather.text = minMaxTemp
                place.text = placeWeather
                weatherType.text = weather.weather[0].main
                date.text = weather.dtTxt
            }
        }
    }
}