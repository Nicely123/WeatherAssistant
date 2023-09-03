package com.example.weatherassistant.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherassistant.database.data.DatabaseCity

@Dao
interface CityDao {
    @Query("SELECT * FROM database_city")
    fun getAll(): LiveData<List<DatabaseCity>>

    @Query("SELECT * FROM database_city WHERE province LIKE '%' || :city || '%' OR city LIKE '%' || :city || '%' OR district LIKE '%' || :city || '%'")
    fun findByCity(city: String): LiveData<List<DatabaseCity>?>

    @Insert
    fun insertAll(cityList: List<DatabaseCity>)

    @Delete
    fun delete(city: DatabaseCity)
}