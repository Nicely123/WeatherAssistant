package com.example.weatherassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.weatherassistant.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        bind()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun bind(){
        val bottomNavigation = binding.bottomNavigation
        bottomNavigation.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener {
            when(it.itemId){
                R.id.weather_browsing_menu -> {
                    navController.navigate(R.id.weatherBrowsingFragment)
                    true
                }
                R.id.joke_menu -> {
                    navController.navigate(R.id.jokeFragment)
                    true
                }
                R.id.person_menu -> {
                    navController.navigate(R.id.personalCenterFragment)
                    true
                }
                else -> false
            }
        })
    }


}