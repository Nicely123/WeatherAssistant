package com.example.weatherassistant.network.city

import com.example.weatherassistant.database.data.DatabaseCity
import com.example.weatherassistant.domain.DisplayedCity

/**
 * 网络获取的City
 */
data class NetWorkCity(
    val id: String,
    val province: String,
    val city: String,
    val district: String
)

/**
 * 网络获取对象转化为Domain Model对象
 */
fun List<NetWorkCity>.asDomainModel(): List<DisplayedCity>{
    return map {
        DisplayedCity(
            id = it.id,
            province = it.province,
            city = it.city,
            district = it.district
        )
    }
}

/**
 * 网络获取对象转化为数据库对象
 */
fun List<NetWorkCity>.asDatabaseModel(): List<DatabaseCity>{
    return map {
        DatabaseCity(
            id = it.id.toLong(),
            province = it.province,
            city = it.city,
            district = it.district
        )
    }
}