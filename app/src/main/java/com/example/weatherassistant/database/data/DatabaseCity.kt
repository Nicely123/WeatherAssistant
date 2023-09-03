package com.example.weatherassistant.database.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherassistant.domain.DisplayedCity

/**
 * DatabaseCity 在数据库中代表一个city实体
 */
@Entity(tableName = "database_city")
data class DatabaseCity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val province: String,
    val city: String,
    val district: String
)

/**
 * 将DatabaseCity映射到 domain entities
 */
fun List<DatabaseCity>.asDomainModel(): List<DisplayedCity>{
    return map {
        DisplayedCity(
            id = it.id.toString(),
            province = it.province,
            city = it.city,
            district = it.district
        )
    }
}