package com.example.weatherassistant.database.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherassistant.domain.DisplayJoke

@Entity(tableName = "joke")
data class DatabaseJoke(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,
    @ColumnInfo(name = "content")
    val content: String,
    @ColumnInfo(name = "hashId")
    val hashId: String,
    @ColumnInfo(name = "unixtime")
    val unixtime: String    // 时间戳
)

/**
 * 将数据库Joke对象装换为界面显示对象
 */
fun List<DatabaseJoke>.asDomainModel():List<DisplayJoke>{
    return map {
        DisplayJoke(
            content = it.content,
            hashId = it.hashId,
            unixtime = it.unixtime
        )
    }
}