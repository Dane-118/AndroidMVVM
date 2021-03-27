package com.example.android.study.push.core.queue

import com.example.android.study.push.entity.CustomMessage
import com.example.android.study.push.entity.Notification
import com.example.android.study.push.entity.XPushCommand

/**
 * 推送消息观察者
 *
 * @author xuexiang
 * @since 2019-08-17 14:33
 */
interface IMessageObserver {
    /**
     * 消息推送连接状态发生变化
     *
     * @param connectStatus 连接状态
     */
    fun onConnectStatusChanged(connectStatus: Int)

    /**
     * 收到通知
     *
     * @param notification 通知
     */
    fun onNotification(notification: Notification?)

    /**
     * 收到通知点击事件
     *
     * @param notification 通知
     */
    fun onNotificationClick(notification: Notification?)

    /**
     * 收到自定义消息
     *
     * @param message 自定义消息
     */
    fun onMessageReceived(message: CustomMessage?)

    /**
     * 收到命令执行的结果
     *
     * @param command 命令
     */
    fun onCommandResult(command: XPushCommand?)
}