package com.example.weatherassistant.ui.joke

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.ItemJokeBinding
import com.example.weatherassistant.domain.DisplayJoke

class JokeAdapter: RecyclerView.Adapter<ViewHolder>() {

    private var dataList = emptyList<DisplayJoke>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return JokeViewHolder(ItemJokeBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val displayJoke = dataList[position]
        if (holder is JokeViewHolder){
            holder.bind(displayJoke)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitData(newData: List<DisplayJoke>?){
        newData?.let {
            dataList = newData
            notifyDataSetChanged()
        }
    }

    class JokeViewHolder(private val binding: ItemJokeBinding): ViewHolder(binding.root){
        fun bind(joke: DisplayJoke){
            binding.tvJoke.text = joke.content
            binding.tvJokeTitle.text = binding.root.resources.getString(R.string.joke_title)
        }
    }

}