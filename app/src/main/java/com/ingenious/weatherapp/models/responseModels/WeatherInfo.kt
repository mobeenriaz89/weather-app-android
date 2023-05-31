package com.ingenious.weatherapp.models.responseModels


import com.google.gson.annotations.SerializedName

class WeatherInfo : ArrayList<WeatherInfo.WeatherInfoItem>(){
    data class WeatherInfoItem(
        @SerializedName("EpochTime")
        var epochTime: Int,
        @SerializedName("HasPrecipitation")
        var hasPrecipitation: Boolean,
        @SerializedName("IsDayTime")
        var isDayTime: Boolean,
        @SerializedName("Link")
        var link: String,
        @SerializedName("LocalObservationDateTime")
        var localObservationDateTime: String,
        @SerializedName("MobileLink")
        var mobileLink: String,
        @SerializedName("PrecipitationType")
        var precipitationType: Any,
        @SerializedName("Temperature")
        var temperature: Temperature,
        @SerializedName("WeatherIcon")
        var weatherIcon: Int,
        @SerializedName("WeatherText")
        var weatherText: String
    ) {
        data class Temperature(
            @SerializedName("Imperial")
            var imperial: Imperial,
            @SerializedName("Metric")
            var metric: Metric
        ) {
            data class Imperial(
                @SerializedName("Unit")
                var unit: String,
                @SerializedName("UnitType")
                var unitType: Int,
                @SerializedName("Value")
                var value: Double
            )
    
            data class Metric(
                @SerializedName("Unit")
                var unit: String,
                @SerializedName("UnitType")
                var unitType: Int,
                @SerializedName("Value")
                var value: Double
            )
        }
    }
}