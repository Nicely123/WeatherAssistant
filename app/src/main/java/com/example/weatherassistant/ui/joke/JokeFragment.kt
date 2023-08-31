package com.example.weatherassistant.ui.joke

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.FragmentJokeBinding
import com.example.weatherassistant.databinding.FragmentPersonalCenterBinding


class JokeFragment : Fragment() {
    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }


}