package com.example.android.study.commonres.network.interceptor

import android.content.Intent
import android.util.Log
import cody.bus.ElegantBus
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.chuanglan.shanyan_sdk.OneKeyLoginManager
import com.example.android.study.commonres.app.EventKey
import com.example.android.study.commonres.app.KeyUtils
import com.example.android.study.commonres.app.RouterHub
import com.example.android.study.commonres.ext.navigation
import com.example.android.study.commonres.ext.showMessage
import com.example.android.study.commonres.shanyan.ShanYanSuccess
import com.example.android.study.commonres.shanyan.ShanYanUtils
import me.hgj.jetpackmvvm.base.appContext
import org.json.JSONObject


/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/11/11
 */
open class NavigationCallbackImpl : NavigationCallback {
    private val TAG = "NavigationCallbackImpl"
    override fun onFound(postcard: Postcard?) {

    }

    override fun onLost(postcard: Postcard?) {
    }

    override fun onArrival(postcard: Postcard?) {
    }

    override fun onInterrupt(postcard: Postcard?) {
        Log.d(TAG, "onInterrupt: $postcard")
        /**1/27/21 登录拦截处理 */
        if (postcard?.extras?.getInt(KeyUtils.INTERCEPTOR_LOGIN) == 1) {

            OneKeyLoginManager.getInstance().getPhoneInfo { code, result -> //预取号回调
                Log.e(TAG, "预取号： code==$code   result==$result")
                if (code == 1022) {
                    OneKeyLoginManager.getInstance().setAuthThemeConfig(

                            ShanYanUtils.getCJSConfig(appContext,
                                    onBackCallBack = {
                                        OneKeyLoginManager.getInstance().finishAuthActivity()
                                        OneKeyLoginManager.getInstance().removeAllListener()
                                    },
                                    otherCallBack = {

                                        startLogin(postcard)
                                    }), null)
                    openLoginActivity(postcard)
                } else {
                    startLogin(postcard)
                }
            }

        } else {
            /**1/27/21 12306登录拦截处理 */

            val apply = postcard?.extras?.apply {
                putString(RouterHub.PATH, postcard.path)
            }
            Log.d(TAG, "onInterrupt: ${postcard?.path}")
            RouterHub.LOGIN_12306_ACTIVITY.navigation(bundle = apply, channel = true)
        }


    }

    private fun openLoginActivity(postcard: Postcard?) {
        //拉取授权页方法
        OneKeyLoginManager.getInstance().openLoginAuth(false, { code, result ->
            if (1000 == code) {
                //拉起授权页成功
                Log.d("TAG", "openLoginActivity: $result")
            } else {
                //拉起授权页失败
                startLogin(postcard)

            }
        }, { code, result ->
            when (code) {
                1011 -> {

                    OneKeyLoginManager.getInstance().finishAuthActivity()
                    OneKeyLoginManager.getInstance().removeAllListener()
                }
                1000 -> {
                    //用户点击登录获取token成功
                    val properties = JSONObject(result)
                    val token = properties.optString("token")
                    ElegantBus.getDefault(EventKey.EVENT_TO_LOGIN).post(ShanYanSuccess(token, postcard?.path, postcard?.extras))
                }
                1003 -> {
                    OneKeyLoginManager.getInstance().setLoadingVisibility(false)
//                        val properties = JSONObject(result)
//                        val optString = properties.optString("innerDesc")
                    //无网络
                    showMessage("网络似乎出了点问题...")
                }
                else -> {
                    //用户点击登录获取token失败
                    showMessage("一键登录失败")
                    startLogin(postcard)
                }
            }

        })
    }

    /**
     * 跳转登录
     */
    private fun startLogin(postcard: Postcard?) {
        RouterHub.LOGIN_ACTIVITY.navigation(bundle = postcard?.extras?.apply {
            putString(RouterHub.PATH, postcard.path)
        },launchModel = Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }


}