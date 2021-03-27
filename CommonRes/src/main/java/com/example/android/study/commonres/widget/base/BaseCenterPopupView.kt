package com.example.android.study.commonres.widget.base

import android.content.Context
import com.lxj.xpopup.core.CenterPopupView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/11/9
 */
open class BaseCenterPopupView(context: Context) : CenterPopupView(context), CoroutineScope by MainScope() {

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}