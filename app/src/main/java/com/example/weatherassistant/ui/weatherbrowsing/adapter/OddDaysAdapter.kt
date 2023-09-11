package com.example.weatherassistant.ui.weatherbrowsing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherassistant.R
import com.example.weatherassistant.databinding.ItemRealtimeCardBinding
import com.example.weatherassistant.databinding.ItemWeatherDetailBinding
import com.example.weatherassistant.network.weather.Weather
import com.example.weatherassistant.ui.weatherbrowsing.WeatherBrowsingViewModel
import com.example.weatherassistant.util.weather

private const val TAG = "OddDaysAdapter"
class OddDaysAdapter(
    private val viewModel: WeatherBrowsingViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val onClickCitySelect : ()->Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        private val COMPARATOR = object : DiffUtil.ItemCallback<Weather>(){
            override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem.city == newItem.city
            }

            override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var weatherCondition: Weather? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == 0){
            val binding = ItemRealtimeCardBinding.inflate(layoutInflater, parent, false)
            RealtimeCardViewHolder(binding)
        }else{
            val binding = ItemWeatherDetailBinding.inflate(layoutInflater, parent, false)
            WeatherDetailCardViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(position){
            0 -> { bindRealtimeCardViewHolder(holder) }
            else -> { bindWeatherDetailCardViewHolder(holder) }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    // 更新数据
    fun submitItem(newWeather: Weather){
        weatherCondition = newWeather
        notifyItemRangeChanged(0, 2)
    }

    private fun bindRealtimeCardViewHolder(holder: RecyclerView.ViewHolder){
        val realtimeCardViewHolder: RealtimeCardViewHolder = holder as RealtimeCardViewHolder
        realtimeCardViewHolder.bind()
    }
    private fun bindWeatherDetailCardViewHolder(holder: RecyclerView.ViewHolder){
        val weatherDetailCardViewHolder: WeatherDetailCardViewHolder = holder as WeatherDetailCardViewHolder
        weatherDetailCardViewHolder.bind()
    }
    inner class RealtimeCardViewHolder(private val binding: ItemRealtimeCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){

            binding.apply {
                /*
                 item_realtime_card绑定
                 */
                // 白天、晚上按钮绑定
                radioButton.setOnCheckedChangeListener { group, checkedId ->
                    when(checkedId){
                        R.id.radio_button_day -> {viewModel.setIsNight(false)}
                        R.id.radio_button_night -> {viewModel.setIsNight(true)}
                    }
                }
                // 点击城市选择跳转
                areaTextView.setOnClickListener {
                    onClickCitySelect()
                }
                // 绑定城市显示
                viewModel.currentCity.observe(lifecycleOwner){
                    areaTextView.text = it
                }
                // 天气显示
                viewModel.isNight.observe(lifecycleOwner){ isNight ->
                    viewModel.currentWeather.value?.let { future ->
                        if (isNight){
                            weatherText.text = future.wid.night
                        }else{
                            weatherText.text = future.wid.day
                        }
                    }

                    if(weatherText.text == weather["00"]){
                        ivWeather.setImageResource(R.drawable.ic_sunny)
                    }else{
                        ivWeather.setImageResource(R.drawable.ic_partly_cloudy)
                    }
                }

                viewModel.currentWeather.observe(lifecycleOwner){ futureWeather ->
                    dateTextView.text = futureWeather.date
                    tvTemperature.text = futureWeather.temperature

                    if (viewModel.isNight.value == true){
                        weatherText.text = futureWeather.wid.night
                    }else{
                        weatherText.text = futureWeather.wid.day
                    }
                    if(weatherText.text == weather["00"]){
                        ivWeather.setImageResource(R.drawable.ic_sunny)
                    }else{
                        ivWeather.setImageResource(R.drawable.ic_partly_cloudy)
                    }
                }
                icArrowLeft.setOnClickListener {
                    viewModel.setPreviousDay()
                }
                icArrowRight.setOnClickListener {
                    viewModel.setNextDay()
                }
                // 是否上一天、下一天按钮是否可以点击
                viewModel.isFirstDay.observe(lifecycleOwner){
                    if (it){
                        icArrowLeft.setImageResource(R.drawable.ic_arrow_left_forbid)
                        icArrowLeft.isClickable = false
                    }else{
                        icArrowLeft.setImageResource(R.drawable.ic_arrow_left)
                        icArrowLeft.isClickable = true
                    }
                }
                viewModel.isLastDay.observe(lifecycleOwner){
                    if (it){
                        icArrowRight.setImageResource(R.drawable.ic_arrow_right_forbid)
                        icArrowRight.isClickable = false
                    }else{
                        icArrowRight.setImageResource(R.drawable.ic_arrow_right)
                        icArrowRight.isClickable = true
                    }
                }
            }

        }
    }
    inner class WeatherDetailCardViewHolder(private val binding: ItemWeatherDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.apply {
                viewModel.weatherInfo.observe(lifecycleOwner){ weatherInfo ->
                    viewModel.currentWeather.value?.let {
                        tv6.text = it.direct
                    }
                    tv5.text = weatherInfo.realtime.direct
                    tvWindPower.text = weatherInfo.realtime.power
                    tvAirQuality.text = weatherInfo.realtime.aqi
                }
            }
        }
    }
}

