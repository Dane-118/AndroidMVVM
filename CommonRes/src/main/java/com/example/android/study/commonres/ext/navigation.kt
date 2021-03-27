package com.example.android.study.commonres.ext

import android.app.Activity
import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.example.android.study.commonres.network.interceptor.NavigationCallbackImpl

/**
 * 描述：
 *
 * @author {Wang Peng} by 12/25/20
 */


/**
 * context:当前上下文,需要拦截登录,result_code 时必传
 *
 * bundle: 传递的参数对象使用 bundle 封装
 *
 * channel:绿色通道 不走拦截器
 *
 * 启动模式 launchModel:  Intent.FLAG_ACTIVITY_SINGLE_TOP
 *
 * resultCode  onActivityForResult
 *
 * onArrival:找到路由后的回调,一般在此回调关闭自己
 *
 * onInterrupt:被拦截时回调
 *
 */
fun String.navigation(
        activity: Activity? = null,
        bundle: Bundle? = null,
        channel: Boolean = false,
        launchModel: Int? = null,
        resultCode: Int? = null,
        onInterrupt: ((Postcard?) -> Unit)? = null,
        onArrival: ((Postcard?) -> Unit)? = null,
) {

    val build = ARouter.getInstance().build(this)
    bundle?.let {
        build.with(it)
    }
    if (channel) {
        build.greenChannel()
    }
    launchModel?.let {
        build.addFlags(it)
    }

    if (activity != null) {
        if (resultCode != null) {
            build.navigation(activity, resultCode, object : NavigationCallbackImpl() {
                override fun onFound(postcard: Postcard?) {
                    super.onFound(postcard)
                }

                override fun onLost(postcard: Postcard?) {
                    super.onLost(postcard)
                }

                override fun onArrival(postcard: Postcard?) {
                    super.onArrival(postcard)
                    onArrival?.invoke(postcard)
                }

                override fun onInterrupt(postcard: Postcard?) {
                    super.onInterrupt(postcard)
                    onInterrupt?.invoke(postcard)


                }
            })
        } else {
            build.navigation(activity, object : NavigationCallbackImpl() {
                override fun onFound(postcard: Postcard?) {
                    super.onFound(postcard)
                }

                override fun onLost(postcard: Postcard?) {
                    super.onLost(postcard)
                }

                override fun onArrival(postcard: Postcard?) {
                    super.onArrival(postcard)
                    onArrival?.invoke(postcard)
                }

                override fun onInterrupt(postcard: Postcard?) {
                    super.onInterrupt(postcard)
                    onInterrupt?.invoke(postcard)
                }
            })
        }


    } else {
        build.navigation()
    }
}








