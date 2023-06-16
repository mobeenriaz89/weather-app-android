package com.ingenious.weatherapp.webApi

import com.ingenious.weatherapp.models.responseModels.WeatherInfo
import com.ingenious.weatherapp.utils.AppConstants
import retrofit2.http.GET

interface WeatherApi {
    @GET(AppConstants.WebApi.currentConditions + "/258278")
    suspend fun getCurrentConditions() : WeatherInfo
}