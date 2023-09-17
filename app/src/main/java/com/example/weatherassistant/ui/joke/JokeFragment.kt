package com.example.weatherassistant.ui.joke

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.weatherassistant.BaseApplication
import com.example.weatherassistant.databinding.FragmentJokeBinding
import com.example.weatherassistant.domain.DisplayJoke

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
        val rv = binding.recycleView
        rv.apply {
            adapter = jokeAdapter
            layoutManager = SlideCardLayoutManager()
        }
        // 初始化数据
        CardConfig.initConfig(requireContext())
        val mDataJokes = mutableListOf<DisplayJoke>()
        jokeViewModel.displayJokes.observe(viewLifecycleOwner){
            it?.let { data ->
                mDataJokes.addAll(data)
                jokeAdapter.submitData(data)
                // 创建ItemTouchHelper，必须需要使用ItemTouchHelper.Callback
                val itemTouchHelper = ItemTouchHelper(SlideCardCallback(
                    jokeAdapter,
                    mDataJokes
                ))
                itemTouchHelper.attachToRecyclerView(rv)

            }
        }
    }
}