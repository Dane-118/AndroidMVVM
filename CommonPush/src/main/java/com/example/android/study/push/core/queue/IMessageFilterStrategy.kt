package com.example.android.study.push.core.queue

import com.example.android.study.push.entity.CustomMessage
import com.example.android.study.push.entity.Notification

/**
 * 推送消息过滤策略
 *
 * @author xuexiang
 * @since 2019-08-25 19:51
 */
interface IMessageFilterStrategy {
    /**
     * 过滤通知
     *
     * @param notification 通知
     * @return `true`：过滤通知 <br></br> `false`：不过滤通知
     */
    fun filterNotification(notification: Notification?): Boolean

    /**
     * 过滤自定义(透传)消息
     *
     * @param message 自定义消息
     * @return `true`：过滤自定义消息 <br></br> `false`：不过滤自定义消息
     */
    fun filterCustomMessage(message: CustomMessage?): Boolean

    /**
     * 增加消息过滤器
     *
     * @param filter 消息过滤器
     */
    fun addFilter(filter: IMessageFilter)

    /**
     * 增加消息过滤器
     *
     * @param index  索引
     * @param filter 消息过滤器
     */
    fun addFilter(index: Int, filter: IMessageFilter)

    /**
     * 增加消息过滤器
     *
     * @param filters 消息过滤器
     */
    fun addFilters(vararg filters: IMessageFilter)

    /**
     * 设置消息过滤器
     *
     * @param filters 消息过滤器
     */
    fun setFilters(vararg filters: IMessageFilter)

    /**
     * 清除消息过滤器
     *
     * @param filter 消息过滤器
     * @return 是否清除成功
     */
    fun removeFilter(filter: IMessageFilter): Boolean

    /**
     * 清除消息过滤器
     *
     * @param filters 消息过滤器
     */
    fun removeFilters(vararg filters: IMessageFilter)

    /**
     * 清除所有的消息过滤器
     */
    fun removeAll()
}