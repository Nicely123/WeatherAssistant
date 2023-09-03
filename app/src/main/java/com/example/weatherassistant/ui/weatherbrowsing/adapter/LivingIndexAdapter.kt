package com.example.weatherassistant.ui.weatherbrowsing.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherassistant.databinding.ItemLivingDetailBinding
import com.example.weatherassistant.databinding.ItemLivingDressBinding
import com.example.weatherassistant.ui.weatherbrowsing.WeatherBrowsingViewModel
import com.example.weatherassistant.util.getCurrentDate

class LivingIndexAdapter(
    val viewModel: WeatherBrowsingViewModel,
    val lifecycleOwner: LifecycleOwner
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /*
        根据不同Item位置设置不同标识
     */
    override fun getItemViewType(position: Int): Int {
        return position
    }

    /*
    根据Item的标识创建不同的ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(viewType){
            0 -> {
                val livingDressBinding = ItemLivingDressBinding.inflate(layoutInflater, parent, false)
                LivingDressViewHolder(livingDressBinding)
            }

            else -> {
                val livDetailBinding = ItemLivingDetailBinding.inflate(layoutInflater, parent, false)
                LivingDetailViewHolder(livDetailBinding)
            }
        }
    }

    /*
    将获取的数据绑定到ViewHolder持有的视图上
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when(position){
           0 -> { bindLivingDressViewHolder(holder) }
           else -> { bindLivingDetailViewHolder(holder) }
       }
    }

    /*
    目前只有两个Item
     */
    override fun getItemCount(): Int {
        return 2
    }

    private fun bindLivingDressViewHolder(holder: RecyclerView.ViewHolder){
        val livingDressViewHolder: LivingDressViewHolder = holder as LivingDressViewHolder
        livingDressViewHolder.bind()
    }
    private fun bindLivingDetailViewHolder(holder: RecyclerView.ViewHolder){
        val livingDetailViewHolder: LivingDetailViewHolder = holder as LivingDetailViewHolder
        livingDetailViewHolder.bind()
    }
    inner class LivingDressViewHolder(private val binding: ItemLivingDressBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.apply {
                dateTextView.text = getCurrentDate()
                // 绑定城市显示
                viewModel.currentCity.observe(lifecycleOwner){
                    areaTextView.text = it
                }
                viewModel.lifeAdvice.observe(lifecycleOwner){ lifeAdvice ->
                    tvSummerDress.text = lifeAdvice.chuanyi.v
                    tvDressTips.text = lifeAdvice.chuanyi.des
                }
            }
        }
    }
    inner class LivingDetailViewHolder(private val binding: ItemLivingDetailBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(){
            binding.apply {
                viewModel.lifeAdvice.observe(lifecycleOwner){ lifeAdvice ->
                    tvAirCondition.text = lifeAdvice.kongtiao.v
                    tvIrritability.text = lifeAdvice.guomin.v
                    tvFlu.text = lifeAdvice.ganmao.v
                    tvFish.text = lifeAdvice.diaoyu.v
                    tvCleanCar.text = lifeAdvice.xiche.v
                    tvSport.text = lifeAdvice.yundong.v
                    tvTakeUmbrella.text = lifeAdvice.daisan.v
                    tvComfortLevel.text = lifeAdvice.shushidu.v
                }
            }
        }
    }
}



