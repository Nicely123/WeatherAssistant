package com.example.weatherassistant.ui.person

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.FragmentPersonalCenterBinding


class PersonalCenterFragment : Fragment() {
    private var _binding: FragmentPersonalCenterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonalCenterBinding.inflate(inflater, container, false)
        return binding.root
    }

}