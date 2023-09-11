package com.example.weatherassistant.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.weatherassistant.database.data.DatabaseJoke

@Dao
interface JokeDao {

    /**
     * 从数据库中随机获取十条数据
     */
    @Query("SELECT * FROM joke ORDER BY RANDOM() LIMIT 10")
    fun getJokes(): LiveData<List<DatabaseJoke>>

    @Insert
    fun insertJokes(jokes: List<DatabaseJoke>)

    @Delete
    fun deleteJoke(joke: DatabaseJoke)
}