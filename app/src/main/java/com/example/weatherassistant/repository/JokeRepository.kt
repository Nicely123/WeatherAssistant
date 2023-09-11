package com.example.weatherassistant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weatherassistant.database.JokeDao
import com.example.weatherassistant.database.data.asDomainModel
import com.example.weatherassistant.domain.DisplayJoke
import com.example.weatherassistant.network.JokeApi
import com.example.weatherassistant.network.joke.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG = "JokeRepository"
class JokeRepository(
    private val jokeDao: JokeDao
) {

    val jokes: LiveData<List<DisplayJoke>?> = Transformations.map(jokeDao.getJokes()){
        it.asDomainModel()
    }

    fun fetchJokes(): LiveData<List<DisplayJoke>?>{
        return fetchJokesFromLocal()
    }

    /**
     * 从网络中获取数据
     */
    suspend fun storeJokes(){
        withContext(Dispatchers.IO){
            val netJoke = JokeApi.retrofitService.getJoke()
            val databaseJokes = netJoke.networkJoke.asDatabaseModel()
            jokeDao.insertJokes(databaseJokes)
        }
    }

    /**
     * 从本地数据库中获取笑话
     */
    private fun fetchJokesFromLocal(): LiveData<List<DisplayJoke>?>{
        val databaseJoke = jokeDao.getJokes()
        val displayJokes = Transformations.map(databaseJoke){
            it.asDomainModel()
        }
        return displayJokes
    }

}