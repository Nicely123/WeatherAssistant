package com.example.weatherassistant.ui.weatherbrowsing

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherassistant.network.weather.Future
import com.example.weatherassistant.network.weather.Weather
import com.example.weatherassistant.network.WeatherApi
import com.example.weatherassistant.network.life.Life
import com.example.weatherassistant.network.weather.Wid
import com.example.weatherassistant.util.next5Day
import com.example.weatherassistant.util.weather
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class WeatherBrowsingViewModel: ViewModel() {

    private var _weatherInfo: MutableLiveData<Weather> = MutableLiveData()
    val weatherInfo get() = _weatherInfo

    private var currentShowIndex = 0

    private var _currentWeather:MutableLiveData<Future> = MutableLiveData()
    val currentWeather get() = _currentWeather

    private var _isFirstDay: MutableLiveData<Boolean> = MutableLiveData(true)
    val isFirstDay get() = _isFirstDay

    private var _isLastDay: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLastDay get() = _isLastDay

    private var _isNight: MutableLiveData<Boolean> = MutableLiveData(false)
    val isNight get() = _isNight

    private var _lifeAdvice: MutableLiveData<Life> = MutableLiveData()
    val lifeAdvice get() = _lifeAdvice

    init {
        fetchData("北碚")
    }
    private fun fetchData(city: String){
        fetchWeather(city)
        fetchLifeAdvice(city)
    }

    private fun fetchWeather(city: String){
        viewModelScope.launch {
            val weatherResult = WeatherApi.retrofitService.getWeather(city)
            if (weatherResult.errorCode == 0){
                _weatherInfo.value = weatherResult.weather
                val future = weatherResult.weather.future[0]
                currentWeather.value = formatWeather(future)
            }
        }
    }
    private fun fetchLifeAdvice(city: String){
        viewModelScope.launch {
            val lifeResult = WeatherApi.retrofitService.getLife(city)
            if (lifeResult.errorCode == 0){
                _lifeAdvice.value = lifeResult.result.life
            }
        }
    }
    private fun formatWeather(future: Future): Future {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())
        val date = inputFormat.parse(future.date)
        val formatDate = outputFormat.format(date!!)
        return future.copy(
            date = formatDate,
            temperature = future.temperature.replace("/", "~").replace("℃", ""),
            wid = Wid(
                weather[future.wid.day]!!,
                weather[future.wid.night]!!
            )
        )
    }


    /*
    是否点击了白天、夜晚按钮 false为白天，true为夜晚
     */
    fun setIsNight(isNight: Boolean){
        _isNight.value = isNight
    }

    // 设置下一天日期
    fun setNextDay(){
        if (currentShowIndex != next5Day[4]) {
            currentShowIndex++
            _currentWeather.value = formatWeather(weatherInfo.value!!.future[currentShowIndex])
        }
        _isLastDay.value = currentShowIndex == next5Day[4]
        _isFirstDay.value = currentShowIndex == next5Day[0]
    }

    // 设置上一天日期
    fun setPreviousDay(){
        if (currentShowIndex != next5Day[0]) {
            currentShowIndex--
            _currentWeather.value = formatWeather(weatherInfo.value!!.future[currentShowIndex])
        }
        _isFirstDay.value = currentShowIndex == next5Day[0]
        _isLastDay.value = currentShowIndex == next5Day[4]
    }
}