package com.example.weatherassistant

import android.app.Application
import com.example.weatherassistant.database.AppDatabase

class BaseApplication: Application() {
    val appDatabase: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}