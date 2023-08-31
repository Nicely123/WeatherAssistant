package com.example.weatherassistant.ui.weatherbrowsing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherassistant.databinding.FragmentWeatherBrowsingBinding
import com.google.android.material.tabs.TabLayoutMediator

class WeatherBrowsingFragment: Fragment(){
    private var _binding: FragmentWeatherBrowsingBinding? = null
    private val binding get() = _binding!!

    private lateinit var weatherBrowsingAdapter: WeatherBrowsingAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBrowsingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherBrowsingAdapter = WeatherBrowsingAdapter(this)
        viewPager = binding.pager
        viewPager.adapter = weatherBrowsingAdapter
        TabLayoutMediator(binding.tabLayout, viewPager){ tab, position ->
            when(position){
                0 -> {tab.text = "单日"}
                1 -> {tab.text = "生活指数"}
            }
        }.attach()
        bind()
    }

    private fun bind(){
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}