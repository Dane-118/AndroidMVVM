package com.example.android.study.push.core.receiver.impl

import android.content.Context
import com.example.android.study.push.core.XPushManager
import com.example.android.study.push.entity.XPushCommand
import com.example.android.study.push.entity.XPushMsg

/**
 * 消息推送统一接收管理
 *
 * @author xuexiang
 * @since 2019-08-17 15:58
 */
open class XPushReceiver : AbstractPushReceiver() {
    override fun onNotification(context: Context?, msg: XPushMsg?) {
        if (msg == null) {
            return
        }
        XPushManager.get().notifyNotification(msg.toNotification())
    }

    /**
     * 收到通知点击事件
     *
     * @param context
     * @param msg     消息
     */
    override fun onNotificationClick(context: Context?, msg: XPushMsg?) {
        if (msg == null) {
            return
        }
        XPushManager.get().notifyNotificationClick(msg.toNotification())
    }

    /**
     * 收到自定义消息
     *
     * @param context
     * @param msg     消息
     */
    override fun onMessageReceived(context: Context?, msg: XPushMsg?) {
        if (msg == null) {
            return
        }
        XPushManager.get().notifyMessageReceived(msg.toCustomMessage())
    }

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
    override fun onCommandResult(context: Context?, command: XPushCommand?) {
        if (command == null) {
            return
        }
        XPushManager.get().notifyCommandResult(command)
    }
}