package com.example.android.study.commonres.utils

import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.View.MeasureSpec.AT_MOST
import androidx.recyclerview.widget.GridLayoutManager
import com.blankj.utilcode.util.SizeUtils

/**
 * 描述：GridLayoutManager设置最大高度
 *
 * @author JiaoPeng by 1/28/21
 */
open class GridLayoutMaxHeightManager(context: Context,
                                      span: Int,
                                      private var maxHeight: Int = SizeUtils.dp2px(300F)
) : GridLayoutManager(context, span) {

    override fun setMeasuredDimension(childrenBounds: Rect, wSpec: Int, hSpec: Int) {
        super.setMeasuredDimension(childrenBounds, wSpec, View.MeasureSpec.makeMeasureSpec(maxHeight, AT_MOST))
    }
}