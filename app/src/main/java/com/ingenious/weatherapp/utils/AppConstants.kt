package com.ingenious.weatherapp.utils

object AppConstants {
    object WebApi{
        const val baseUrl = "http://dataservice.accuweather.com"
        const val apiKey = "rnpyNOPkBDXgzNgDCoyqPbzIVLcPJ5Qk"
        const val apiVersion = "/v1"
        const val currentConditions = "/currentconditions$apiVersion"
        const val forecasts_daily_5days = "/forecasts$apiVersion/daily/5day"
        const val weathericonPrefix = "https://developer.accuweather.com/sites/default/files/"
        const val weathericonPostfix = "-s.png"
    }
}