package com.ingenious.weatherapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ingenious.weatherapp.R
import com.ingenious.weatherapp.controllers.DataStoreController
import com.ingenious.weatherapp.models.responseModels.WeatherInfo
import com.ingenious.weatherapp.utils.AppConstants
import com.ingenious.weatherapp.utils.UiUtils
import com.ingenious.weatherapp.webApi.RetrofitController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class HomeActivity : AppCompatActivity() {

    lateinit var store: DataStoreController

    lateinit var tvTemp: TextView
    lateinit var tvCondition: TextView
    lateinit var imgWeather: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        store = DataStoreController(this)
        initViews()
        //UiUtils.loadImage(this,"https://developer.accuweather.com/sites/default/files/01-s.png",imgWeather)


    }

    override fun onResume() {
        super.onResume()
        getLocationKey()
    }

    private fun initViews() {
        tvTemp = findViewById(R.id.tvTemp)
        tvCondition = findViewById(R.id.tvCondition)
        imgWeather = findViewById(R.id.imgWeather)
    }

    private fun getLocationKey() {
        val locationKey = "258278" // will get it from Api later

        CoroutineScope(Dispatchers.IO).launch {
            store.setLocationKey(locationKey = locationKey)
            getCurrentConditions()
        }
    }

    fun getCurrentConditions(){
        val service = RetrofitController.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = service.getCurrentConditions()
                Log.i("=Current Conditions=", response.toString())
                populateViews(response)
            }catch (e: Exception){
                e.printStackTrace()
            }

        }
    }

    private fun populateViews(info: WeatherInfo) {
        val tempItem = info.first()

        val tempVal = tempItem.temperature.metric.value.roundToInt()
        tvTemp.text = tempVal.toString()

        tvCondition.text = tempItem.weatherText
        var weatherIconVal = tempItem.weatherIcon.toString()
        if(weatherIconVal.length == 1)
            weatherIconVal = "0$weatherIconVal"
        val tempImage = AppConstants.WebApi.weathericonPrefix + weatherIconVal + AppConstants.WebApi.weathericonPostfix
        runOnUiThread{
            UiUtils.loadImage(this@HomeActivity,tempImage,imgWeather)
        }

    }
}