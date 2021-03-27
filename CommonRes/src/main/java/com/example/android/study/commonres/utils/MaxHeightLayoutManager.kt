package com.example.android.study.commonres.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.View.MeasureSpec.AT_MOST
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.SizeUtils

/**
 * 描述：设置Recyclerview固定高度
 *
 * @author {Wang Peng} by 2020/7/22
 */
class MaxHeightLayoutManager(context: Context,
                             private var maxHeight:Int = SizeUtils.dp2px(300F)

) : LinearLayoutManager(context) {

    override fun setMeasuredDimension(childrenBounds: Rect, wSpec: Int, hSpec: Int) {
        super.setMeasuredDimension(childrenBounds, wSpec, View.MeasureSpec.makeMeasureSpec(maxHeight, AT_MOST))
    }
}