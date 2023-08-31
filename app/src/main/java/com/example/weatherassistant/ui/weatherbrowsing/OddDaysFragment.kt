package com.example.weatherassistant.ui.weatherbrowsing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.FragmentOddDaysBinding
import com.example.weatherassistant.ui.weatherbrowsing.adapter.OddDaysAdapter

class OddDaysFragment : Fragment() {

    companion object{
        const val ARG_ODD_DAYS_FRAGMENT = "odd_days_fragment"
    }

    private var _binding: FragmentOddDaysBinding? = null
    private val binding get() = _binding!!

    private val weatherBrowsingViewModel: WeatherBrowsingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOddDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycleView = binding.recycleView
        val adapter = OddDaysAdapter(weatherBrowsingViewModel, viewLifecycleOwner){
            this.findNavController().navigate(R.id.action_weatherBrowsingFragment_to_citySelectFragment)
        }
        weatherBrowsingViewModel.weatherInfo.observe(viewLifecycleOwner){
            adapter.submitItem(it)
        }
        recycleView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }
}