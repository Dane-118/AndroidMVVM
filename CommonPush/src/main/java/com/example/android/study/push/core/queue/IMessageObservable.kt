package com.example.android.study.push.core.queue

import com.example.android.study.push.entity.Notification
import com.example.android.study.push.entity.XPushCommand
import com.example.android.study.push.entity.CustomMessage
/**
 * 推送消息的被观察者实现接口
 *
 * @author xuexiang
 * @since 2019-08-17 15:11
 */
interface IMessageObservable {
    /**
     * 消息推送连接状态发生变化
     *
     * @param connectStatus 推送连接状态
     */
    fun notifyConnectStatusChanged(connectStatus: Int)

    /**
     * 收到通知
     *
     * @param notification 被处理过的推送消息
     */
    fun notifyNotification(notification: Notification?)

    /**
     * 收到通知点击事件
     *
     * @param notification 被处理过的推送消息
     */
    fun notifyNotificationClick(notification: Notification?)

    /**
     * 收到自定义消息
     *
     * @param message 自定义消息
     */
    fun notifyMessageReceived(message: CustomMessage?)

    /**
     * 收到命令执行的结果
     *
     * @param command 命令
     */
    fun notifyCommandResult(command: XPushCommand?)

    /**
     * 注册推送消息的订阅者
     *
     * @param observer 消息订阅者
     * @return 是否注册成功
     */
    fun register(observer: IMessageObserver?): Boolean

    /**
     * 注销推送消息的订阅者
     *
     * @param observer 消息订阅者
     * @return 是否注销成功
     */
    fun unregister(observer: IMessageObserver?): Boolean

    /**
     * 注销所有的推送消息的订阅者
     */
    fun unregisterAll()
}