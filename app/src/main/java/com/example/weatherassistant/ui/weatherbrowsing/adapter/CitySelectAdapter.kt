package com.example.weatherassistant.ui.weatherbrowsing.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.ItemCityDisplayBinding
import com.example.weatherassistant.domain.DisplayedCity

class CitySelectAdapter(
    private val callback: (DisplayedCity) -> Unit
): ListAdapter<DisplayedCity, CitySelectAdapter.CityDisplayViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<DisplayedCity>() {
            override fun areItemsTheSame(oldItem: DisplayedCity, newItem: DisplayedCity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DisplayedCity,
                newItem: DisplayedCity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityDisplayViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCityDisplayBinding.inflate(layoutInflater, parent, false)
        return CityDisplayViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityDisplayViewHolder, position: Int) {
        val city = getItem(position)
        holder.itemView.setOnClickListener{
            callback(city)
        }
        holder.bind(city)
    }

    class CityDisplayViewHolder(private val binding: ItemCityDisplayBinding): ViewHolder(binding.root){
        fun bind(displayedCity: DisplayedCity){
            // 修改成从Recourse中获取
            binding.tvCity.text = binding.root.resources.getString(
                R.string.city_text,
                displayedCity.district, displayedCity.city,
                if (displayedCity.city == displayedCity.province)
                    "中国"
                else displayedCity.province
            )
        }
    }
}