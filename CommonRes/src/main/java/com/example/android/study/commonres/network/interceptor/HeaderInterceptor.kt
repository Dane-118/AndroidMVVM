package com.example.android.study.commonres.network.interceptor

import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.example.android.study.commonres.network.AppConstant.CLIENT_ID
import com.example.android.study.commonres.network.AppConstant.User_Agent
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/8/6
 */
class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        var appVersionName = ""
        var deviceId = ""
        try {
            appVersionName = AppUtils.getAppVersionName()
            deviceId = DeviceUtils.getUniqueDeviceId(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        //正常添加 token 进行请求
        val newHeader = request.newBuilder()
                .header("User-Agent", User_Agent)
                .header("app-version", appVersionName)
                .header("deviceId", deviceId)
                .header("client-id", CLIENT_ID)
        return chain.proceed(newHeader.build())
    }
}