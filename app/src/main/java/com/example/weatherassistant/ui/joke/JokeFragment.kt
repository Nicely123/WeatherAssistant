package com.example.weatherassistant.ui.joke

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherassistant.BaseApplication
import com.example.weatherassistant.databinding.FragmentJokeBinding

private const val TAG = "JokeFragment"
class JokeFragment : Fragment() {
    private var _binding: FragmentJokeBinding? = null
    private val binding get() = _binding!!

    private val jokeViewModel: JokeViewModel by viewModels {
        JokeViewModelFactory(
            (activity?.application as BaseApplication).appDatabase.jokeDao()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentJokeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val jokeAdapter = JokeAdapter()
        jokeViewModel.displayJokes.observe(viewLifecycleOwner){
            Log.v(TAG, it.toString())
            jokeAdapter.submitData(it)
        }
        binding.recycleView.apply {
            adapter = jokeAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }


}