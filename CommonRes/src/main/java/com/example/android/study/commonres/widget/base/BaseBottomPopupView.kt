package com.example.android.study.commonres.widget.base

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.lxj.xpopup.core.BottomPopupView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/11/9
 */
open class BaseBottomPopupView(context: Context) : BottomPopupView(context), CoroutineScope by MainScope(), LifecycleOwner {

    private lateinit var mLifecycleRegistry: LifecycleRegistry
    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }

    override fun getMaxHeight(): Int {
        return super.getMaxHeight()
    }

    override fun onCreate() {
        super.onCreate()
        mLifecycleRegistry = LifecycleRegistry(this)
    }

    override fun getLifecycle(): Lifecycle = mLifecycleRegistry

}