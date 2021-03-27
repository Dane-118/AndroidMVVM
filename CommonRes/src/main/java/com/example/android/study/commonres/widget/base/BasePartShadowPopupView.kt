package com.example.android.study.commonres.widget.base

import android.content.Context
import com.lxj.xpopup.impl.PartShadowPopupView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/11/9
 */
open class BasePartShadowPopupView(context: Context) : PartShadowPopupView(context), CoroutineScope by MainScope() {

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

}