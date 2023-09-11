package com.example.weatherassistant.network.joke

import com.example.weatherassistant.database.data.DatabaseJoke
import com.example.weatherassistant.domain.DisplayJoke

data class NetworkJoke(
    val content: String,
    val hashId: String,
    val unixtime: String    // 时间戳
)

/**
 * 将从服务器中获取的Joke对象装换为界面显示的Joke对象
 */
fun List<NetworkJoke>.asDomainModel(): List<DisplayJoke>{
    return map {
        DisplayJoke(
            content = it.content,
            hashId = it.hashId,
            unixtime = it.unixtime
        )
    }
}

/**
 * 将从服务器中获取的Joke对象装换为数据库Joke对象
 */
fun List<NetworkJoke>.asDatabaseModel(): List<DatabaseJoke>{
    return map {
        DatabaseJoke(
            content = it.content,
            hashId = it.hashId,
            unixtime = it.unixtime
        )
    }
}