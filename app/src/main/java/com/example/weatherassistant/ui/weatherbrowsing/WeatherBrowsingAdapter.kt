package com.example.weatherassistant.ui.weatherbrowsing

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherassistant.network.weather.Weather

class WeatherBrowsingAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    private lateinit var weatherCondition: Weather

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> bindOddDaysFragment(position)
            else -> bindLivingIndexFragment(position)
        }
    }

    private fun bindOddDaysFragment(position: Int): Fragment{
        val oddDaysFragment = OddDaysFragment()
        oddDaysFragment.arguments = Bundle().apply {
            // 界面传值
            putInt(OddDaysFragment.ARG_ODD_DAYS_FRAGMENT, position)
        }
        return oddDaysFragment
    }

    private fun bindLivingIndexFragment(position: Int): Fragment{
        val livingIndexFragment = LivingIndexFragment()
        livingIndexFragment.arguments = Bundle().apply {
            // 界面传值
            putInt(LivingIndexFragment.ARG_LIVING_INDEX_FRAGMENT, position)
        }
        return livingIndexFragment
    }

}