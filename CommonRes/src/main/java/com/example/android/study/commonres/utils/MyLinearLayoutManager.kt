package com.example.android.study.commonres.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * 描述：禁止滑动
 *
 * @author {Wang Peng} by 2020/7/22
 */
class MyLinearLayoutManager(context: Context,
                            private var canScrollVertically: Boolean = true,
                            private var canScrollHorizontally: Boolean = true
) : LinearLayoutManager(context) {
    override fun canScrollVertically(): Boolean {
        return canScrollVertically
    }


    override fun canScrollHorizontally(): Boolean {
        return canScrollHorizontally
    }


}