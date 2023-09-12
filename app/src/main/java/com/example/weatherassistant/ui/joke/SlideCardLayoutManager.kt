package com.example.weatherassistant.ui.joke

import android.content.Context
import android.util.TypedValue
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SlideCardLayoutManager: RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    /**
     * 重写布局函数
     */
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        recycler?.let {
            // 回收
            detachAndScrapAttachedViews(it)

            val bottomPosition: Int = if (itemCount < CardConfig.MAX_SHOW_COUNT){
                0
            }else{
                itemCount - CardConfig.MAX_SHOW_COUNT
            }
            for (i in bottomPosition until  itemCount){
                // 复用
                val view = it.getViewForPosition(i)
                addView(view)
                // onMeasure
                measureChildWithMargins(view, 0, 0)
                val widthSpace = width - getDecoratedMeasuredWidth(view)
                val heightSpace = height - getDecoratedMeasuredHeight(view)
                // 布局
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                    widthSpace / 2 + getDecoratedMeasuredWidth(view), heightSpace / 2 + getDecoratedMeasuredHeight(view))
                // 10-6-1   10-7-1  10-8-1  10-9-1
                val level = itemCount - i - 1
                if (level > 0){
                    if (level < 3){ // 2 1
                        view.translationY = level * CardConfig.TRANS_Y_GAP
                        view.scaleX = (1 - level * CardConfig.SCALE_GAP)
                        view.scaleY = (1 - level * CardConfig.SCALE_GAP)
                    }else{  // 3
                        view.translationY = (level - 1) * CardConfig.TRANS_Y_GAP
                        view.scaleX = ( 1 - (level - 1) * CardConfig.SCALE_GAP)
                        view.scaleY = ( 1 - (level - 1) * CardConfig.SCALE_GAP)
                    }
                }
            }
        }
    }
}

object CardConfig{
    // 屏幕上最多显示几个item
    var MAX_SHOW_COUNT: Int = 0
    // 每一级Scale相差0.05
    var SCALE_GAP: Float = 0f
    // 每一级TranslationY相差15dp
    var TRANS_Y_GAP: Float = 0f

    fun initConfig(context: Context){
        MAX_SHOW_COUNT = 4
        SCALE_GAP = 0.05f
        TRANS_Y_GAP = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 15f, context.resources.displayMetrics)
    }
}