package com.example.weatherassistant.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.weatherassistant.database.data.DatabaseCity

@Database(entities = [DatabaseCity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cityDao(): CityDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase ?= null

        fun getDatabase(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "database"
                )
                    .build()
                INSTANCE = db
                db
            }
        }

    }
}

