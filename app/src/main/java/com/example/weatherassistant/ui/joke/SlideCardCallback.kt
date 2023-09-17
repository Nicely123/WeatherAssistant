package com.example.weatherassistant.ui.joke

import android.annotation.SuppressLint
import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherassistant.domain.DisplayJoke
import kotlin.math.sqrt

class SlideCardCallback(
    private val adapter: JokeAdapter,
    private val mData: MutableList<DisplayJoke>
) : ItemTouchHelper.SimpleCallback(0, 15) // 0x1111 设置拖拽方向，上下左右都能滑动
{

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    // 划出屏幕
    @SuppressLint("NotifyDataSetChanged")
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val remove: DisplayJoke = mData.removeAt(viewHolder.layoutPosition)
        mData.add(0, remove)
        adapter.submitData(mData)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

        val maxDistance: Double = recyclerView.width * 0.5
        val distance: Double = sqrt((dX * dX + dY * dY).toDouble())
        // 放大的系数
        var fraction: Double = distance / maxDistance
        if (fraction > 1){
            fraction = 1.0
        }

        val childCount = recyclerView.childCount
        // 0 1 2 3 item从上到下
        for (i in 0 until  childCount){
            val view = recyclerView.getChildAt(i)
            val level = childCount - i - 1
            if (level > 0){
                if (level < CardConfig.MAX_SHOW_COUNT - 1) { // 2 1
                    // 最大达到它的上一个Item的效果
                    view.translationY = CardConfig.TRANS_Y_GAP * (level - fraction).toFloat()
                    view.scaleX = (1 - CardConfig.SCALE_GAP * (level - fraction)).toFloat()
                    view.scaleY = (1 - CardConfig.SCALE_GAP * (level - fraction)).toFloat()
                }
            }
        }
    }

    // 回弹时间
    override fun getAnimationDuration(
        recyclerView: RecyclerView,
        animationType: Int,
        animateDx: Float,
        animateDy: Float
    ): Long {
        return 500
    }

    // 回弹距离
    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return 0.4f
    }
}