package com.example.android.study.push.jpush

import android.content.Context
import android.os.Handler
import android.text.TextUtils
import cn.jpush.android.api.JPushInterface
import com.example.android.study.push.XPush
import com.example.android.study.push.core.IPushClient
import com.example.android.study.push.core.annotation.CommandType
import com.example.android.study.push.core.annotation.ResultCode
import com.example.android.study.push.logs.PushLog
import com.example.android.study.push.util.PushUtils

/**
 * 极光推送客户端
 *
 * @author xuexiang
 * @since 2019-08-16 17:05
 */
class JPushClient : IPushClient {
    private var mContext: Context? = null
    private val mHandler = Handler()
    override fun init(context: Context) {
        mContext = context.applicationContext
        JPushInterface.setDebugMode(PushLog.isDebug())
        JPushInterface.init(context)
    }

    override fun register() {
        if (JPushInterface.isPushStopped(mContext)) {
            JPushInterface.resumePush(mContext)
        }
        val token = JPushInterface.getRegistrationID(mContext)
        if (!TextUtils.isEmpty(token)) {
            XPush.transmitCommandResult(mContext, CommandType.TYPE_REGISTER, ResultCode.RESULT_OK, token, null, null)
        }
    }

    override fun unRegister() {
        JPushInterface.stopPush(mContext)
        mHandler.postDelayed({
            if (JPushInterface.isPushStopped(mContext)) {
                XPush.transmitCommandResult(mContext, CommandType.TYPE_UNREGISTER, ResultCode.RESULT_OK, null, null, null)
            }
        }, 200)
    }

    override fun bindAlias(alias: String) {
        JPushInterface.setAlias(mContext, CommandType.TYPE_BIND_ALIAS, alias)
    }

    override fun unBindAlias(alias: String) {
        JPushInterface.deleteAlias(mContext, CommandType.TYPE_UNBIND_ALIAS)
    }

    /**
     * 获取别名
     */
    override fun getAlias() {
        JPushInterface.getAlias(mContext, CommandType.TYPE_GET_ALIAS)
    }

    override fun addTags(vararg tag: String) {
        JPushInterface.addTags(mContext, CommandType.TYPE_ADD_TAG, PushUtils.array2Set(*tag))
    }

    override fun deleteTags(vararg tag: String) {
        JPushInterface.deleteTags(mContext, CommandType.TYPE_DEL_TAG, PushUtils.array2Set(*tag))
    }

    /**
     * 获取标签
     */
    override fun getTags() {
        JPushInterface.getAllTags(mContext, CommandType.TYPE_GET_TAG)
    }

    /**
     * @return 获取推送令牌
     */
    override fun getPushToken(): String {
        return JPushInterface.getRegistrationID(mContext)
    }

    override fun getPlatformCode(): Int {
        return JPUSH_PLATFORM_CODE
    }

    override fun getPlatformName(): String {
        return JPUSH_PLATFORM_NAME
    }

    companion object {
        const val JPUSH_PLATFORM_NAME = "JPush"
        const val JPUSH_PLATFORM_CODE = 1000
    }
}