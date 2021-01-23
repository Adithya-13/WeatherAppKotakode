package com.codext.project.weatherappkotakode

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("data/2.5/forecast")
    fun getForecast(
        @Query("q") cityName : String,
        @Query("appid") apiKey : String,
        @Query("units") units : String,
    ) : Call<Weather>
}