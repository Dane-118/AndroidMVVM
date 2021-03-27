package me.hgj.jetpackmvvm.ext.lifecycle

import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * 作者　: hegaojian
 * 时间　: 20120/1/7
 * 描述　:
 */
class KtxHandler(lifecycleOwner: LifecycleOwner, looper: Looper, val action: ((Message) -> Unit)) : Handler(looper), LifecycleObserver {

    private val mLifecycleOwner: LifecycleOwner = lifecycleOwner

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        action.invoke(msg)
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        mLifecycleOwner.lifecycle.removeObserver(this)
    }
}