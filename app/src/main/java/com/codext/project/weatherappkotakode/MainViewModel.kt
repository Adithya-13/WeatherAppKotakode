package com.codext.project.weatherappkotakode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _weatherList = MutableLiveData<Weather>()
    val weatherList: LiveData<Weather> = _weatherList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "MainViewModel"
        private const val API_KEY = "9def55156116300705627ea5d432bbc7"
    }

    fun getForecast() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getForecast("majalengka", API_KEY, "metric")
        client.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _weatherList.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}