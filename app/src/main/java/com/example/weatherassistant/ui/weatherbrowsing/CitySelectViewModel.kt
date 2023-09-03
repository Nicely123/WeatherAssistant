package com.example.weatherassistant.ui.weatherbrowsing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherassistant.database.CityDao
import com.example.weatherassistant.database.data.asDomainModel
import com.example.weatherassistant.domain.DisplayedCity
import com.example.weatherassistant.repository.CityRepository
import java.lang.IllegalArgumentException

class CitySelectViewModel(
    private val cityDao: CityDao
):ViewModel() {

    private val cityRepository: CityRepository = CityRepository(cityDao)

    // 所有城市显示
    var cityList: LiveData<List<DisplayedCity>?> = cityRepository.cityList
    init {
//        viewModelScope.launch {
//            cityRepository.storeCityList()
//        }
    }
    /**
     * 查询城市列表
     */
    fun queryCity(query: String){
        val listLiveData = cityRepository.queryCity(query)
        cityList = listLiveData
    }

}

class CitySelectViewModelProvider(private val cityDao: CityDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CitySelectViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CitySelectViewModel(cityDao) as T
        }
        throw IllegalArgumentException("Unable to construct viewModel")
    }

}