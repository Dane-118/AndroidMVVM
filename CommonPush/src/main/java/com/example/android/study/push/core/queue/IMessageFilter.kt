package com.example.android.study.push.core.queue

import com.example.android.study.push.entity.CustomMessage
import com.example.android.study.push.entity.Notification

/**
 * 消息过滤器
 *
 * @author xuexiang
 * @since 2019-08-18 23:44
 */
interface IMessageFilter {
    /**
     * 过滤通知
     *
     * @param notification 通知
     * @return `true`：过滤通知 <br></br> `false`：不过滤通知
     */
    fun filter(notification: Notification?): Boolean

    /**
     * 过滤自定义消息
     *
     * @param message 自定义消息
     * @return `true`：过滤自定义消息 <br></br> `false`：不过滤自定义消息
     */
    fun filter(message: CustomMessage?): Boolean
}