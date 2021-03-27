package com.example.android.study.push.core.receiver

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.example.android.study.push.entity.XPushCommand
import com.example.android.study.push.entity.XPushMsg

/**
 * 消息推送接收器
 *
 * @author xuexiang
 * @since 2019-08-15 13:58
 */
interface IPushReceiver {
    /**
     * 消息推送连接状态发生变化
     *
     * @param context
     * @param connectStatus 连接状态
     */
    fun onConnectStatusChanged(context: Context?, connectStatus: Int)

    /**
     * 收到通知
     *
     * @param context
     * @param msg     消息
     */
    fun onNotification(context: Context?, msg: XPushMsg?)

    /**
     * 收到通知点击事件
     *
     * @param context
     * @param msg     消息
     */
    fun onNotificationClick(context: Context?, msg: XPushMsg?)

    /**
     * 收到自定义消息
     *
     * @param context
     * @param msg     消息
     */
    fun onMessageReceived(context: Context?, msg: XPushMsg?)

    /**
     * IPushClient执行命令的结果返回
     *
     * @param context
     * @param command 命令实体
     * @see IPushClient.register
     * @see IPushClient.unRegister
     * @see IPushClient.addTags
     * @see IPushClient.deleteTags
     * @see IPushClient.getTags
     * @see IPushClient.bindAlias
     * @see IPushClient.unBindAlias
     * @see IPushClient.getAlias
     */
    fun onCommandResult(context: Context?, command: XPushCommand?)

    /**
     * 收到广播后解析消息数据
     *
     * @param intent
     * @param <T>
     * @return
    </T> */
    fun <T : Parcelable?> parsePushData(intent: Intent?): T
}