package com.example.android.study.push.logs

/**
 * 简易的日志接口
 *
 * @author xuexiang
 * @since 2019-08-15 11:27
 */
interface ILogger {
    /**
     * 打印信息
     *
     * @param priority 优先级
     * @param tag      标签
     * @param message  信息
     * @param t        出错信息
     */
    fun log(priority: Int, tag: String?, message: String?, t: Throwable?)
}