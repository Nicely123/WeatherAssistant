package com.example.weatherassistant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weatherassistant.database.CityDao
import com.example.weatherassistant.database.data.asDomainModel
import com.example.weatherassistant.domain.DisplayedCity
import com.example.weatherassistant.network.WeatherApi
import com.example.weatherassistant.network.city.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 将网络中获取的数据保存到硬盘中
 */
class CityRepository(
    private val cityDao: CityDao
) {
    val cityList: LiveData<List<DisplayedCity>?> = Transformations.map(cityDao.getAll()){
        it.asDomainModel()
    }

    suspend fun storeCityList(){
        withContext(Dispatchers.IO){
            val cityResult = WeatherApi.retrofitService.getCityList()
            cityDao.insertAll(cityResult.netWorkCity.asDatabaseModel())
        }
    }

    fun queryCity(query: String): LiveData<List<DisplayedCity>?>{
        return Transformations.map(cityDao.findByCity(query)){it?.asDomainModel()}
    }
}
