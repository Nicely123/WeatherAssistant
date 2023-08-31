package com.example.weatherassistant.util

import android.content.Context
import android.icu.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// dp转为像素
fun Context.dp2px(dp: Int) = resources.displayMetrics.density * dp
// 像素转为dp
fun Context.px2dp(px: Int) = px / resources.displayMetrics.density
// 获取七天日期
fun getFiveDayDate(): List<String>{
    val dateList = mutableListOf<String>()
    val calendar = Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MM月dd日", Locale.CHINA)
    repeat(5){// it belong [0, 5)
        dateList.add(dateFormat.format(calendar.time))
        calendar.add(Calendar.DAY_OF_MONTH, 1)
    }
    return dateList
}

// 获取当前日期
fun getCurrentDate():String{
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("MM月dd日", Locale.getDefault())
    return dateFormat.format(currentDate)
}