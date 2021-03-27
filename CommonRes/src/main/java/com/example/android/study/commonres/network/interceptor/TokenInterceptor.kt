package com.example.android.study.commonres.network.interceptor

import android.content.Intent
import cn.jpush.android.api.JPushInterface
import com.example.android.study.commonres.app.KeyUtils
import com.example.android.study.commonres.app.RouterHub
import com.example.android.study.commonres.ext.mmkv
import com.example.android.study.commonres.ext.navigation
import com.example.android.study.push.core.annotation.CommandType
import me.hgj.jetpackmvvm.base.appContext
import okhttp3.*
import org.json.JSONObject


/**
 * 描述：
 *
 * @author {Wang Peng} by 1/2/21
 */
class TokenInterceptor : Interceptor {

    companion object {
        private const val WAIT_TIME = 1000L * 30
        private var TOUCH_TIME: Long = 0
        const val TOKEN_TIME_OUT = 301009
        const val CODE_TO_12306_LOGIN = 303369
        const val CODE = "code"
        const val MESSAGE = "msg"
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val code = try {
            /**不能直接使用response.body().string()的方式获取请求返回，
            因为response.body().string()之后，response中的流会被关闭
             */
            val responseBody = response.peekBody((1024 * 1024).toLong())
            val responseStr = responseBody.string()
            //判断Token是否过期
            val obj = JSONObject(responseStr)
            obj.getInt(CODE)
        } catch (e: Exception) {
            e.printStackTrace()
            1
        }
        if (code == TOKEN_TIME_OUT) {
            JPushInterface.deleteAlias(appContext, CommandType.TYPE_BIND_ALIAS)
            JPushInterface.deleteTags(appContext, 1, setOf("consumer"))
            mmkv.removeValueForKey(KeyUtils.KEY_TOKEN)
            mmkv.removeValueForKey(KeyUtils.PHONE_NUM)
            mmkv.removeValueForKey(KeyUtils.MEMBER_ID)
            mmkv.removeValueForKey(KeyUtils.USER_INFO)
            mmkv.removeValueForKey(KeyUtils.KEY_BLE_HISTORY_LIST)
            /**
             * 打开登录
             */
            navigation()
        } else if (code == CODE_TO_12306_LOGIN) {
            navigation()
        }
        return response
    }

    /**
     * 30s 内出发一次登录
     */
    private fun navigation() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            RouterHub.LOGIN_ACTIVITY.navigation(launchModel = Intent.FLAG_ACTIVITY_SINGLE_TOP)
        } else {
            TOUCH_TIME = System.currentTimeMillis()
        }
    }


    /**
     * 终止本次请求
     */
    private fun newResponse(chain: Interceptor.Chain, obj: String): Response = Response.Builder()
            .code(444444) // 其实code可以随便给
            .protocol(Protocol.HTTP_2)
            .message("token 过期")
            .body(ResponseBody.create(MediaType.get("text/html; charset=utf-8"), obj)) // 返回空页面
            .request(chain.request())
            .build()


}