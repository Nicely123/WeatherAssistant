package com.example.weatherassistant.ui.weatherbrowsing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.FragmentLivingIndexBinding
import com.example.weatherassistant.ui.weatherbrowsing.adapter.LivingIndexAdapter

class LivingIndexFragment : Fragment() {

    companion object{
        const val ARG_LIVING_INDEX_FRAGMENT = "living_index_fragment"
    }

    private var _binding: FragmentLivingIndexBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherBrowsingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLivingIndexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycleView = binding.recycleView
        val adapter = LivingIndexAdapter(viewModel, viewLifecycleOwner){
            findNavController().navigate(R.id.action_weatherBrowsingFragment_to_citySelectFragment)
        }
        recycleView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.adapter = adapter
        }
    }

}