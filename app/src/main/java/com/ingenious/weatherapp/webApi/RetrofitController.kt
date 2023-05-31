package com.ingenious.weatherapp.webApi

import com.ingenious.weatherapp.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitController {

    fun getInstance(): WeatherApi {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain -> return@addInterceptor addApiKeyToRequests(chain) }
            .build()
        return Retrofit.Builder()
            .baseUrl(AppConstants.WebApi.baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    private fun addApiKeyToRequests(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url()
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey",AppConstants.WebApi.apiKey).build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}