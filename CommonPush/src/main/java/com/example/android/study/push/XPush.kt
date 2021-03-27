package com.example.android.study.push

import android.app.Application
import android.content.Context
import android.content.IntentFilter
import com.example.android.study.push.core.IPushClient
import com.example.android.study.push.core.IPushInitCallback
import com.example.android.study.push.core.XPushManager
import com.example.android.study.push.core.annotation.CommandType
import com.example.android.study.push.core.annotation.ConnectStatus
import com.example.android.study.push.core.annotation.PushAction
import com.example.android.study.push.core.annotation.ResultCode
import com.example.android.study.push.core.dispatcher.IPushDispatcher
import com.example.android.study.push.core.receiver.impl.AbstractPushReceiver
import com.example.android.study.push.entity.XPushMsg
import com.example.android.study.push.logs.ILogger
import com.example.android.study.push.logs.PushLog

/**
 * XPush推送
 *
 * @author xuexiang
 * @since 2019-08-15 10:48
 */
class XPush private constructor() {
    companion object {
        //===============================初始化========================================//
        /**
         * 初始化[不注册推送客户端]
         *
         * @param application
         */
        fun init(application: Application) {
            _XPush.get().init(application)
        }

        /**
         * 设置推送客户端
         *
         * @param pushClient
         */
        fun setIPushClient(pushClient: IPushClient) {
            _XPush.get().setIPushClient(pushClient)
        }

        /**
         * 初始化[自动注册]
         *
         * @param application
         * @param registerCallback 注册回调
         */
        fun init(application: Application, registerCallback: IPushInitCallback) {
            _XPush.get().init(application, registerCallback)
        }

        /**
         * 初始化[手动注册]
         *
         * @param application
         * @param pushClient  推送客户端
         */
        fun init(application: Application, pushClient: IPushClient) {
            _XPush.get().init(application, pushClient)
        }

        @JvmStatic
        val context: Context
            get() = _XPush.get().context
        //===============================日志========================================//
        /**
         * 设置是否打开调试
         *
         * @param isDebug
         */
        fun debug(isDebug: Boolean) {
            PushLog.debug(isDebug)
        }

        /**
         * 设置是否打开调试
         *
         * @param logger
         */
        fun setLogger(logger: ILogger) {
            PushLog.setLogger(logger)
        }
        //===============================操作========================================//
        /**
         * 注册
         */
        fun register() {
            _XPush.get().register()
        }

        /**
         * 注销
         */
        fun unRegister() {
            _XPush.get().unRegister()
        }

        /**
         * 绑定别名
         *
         * @param alias 别名
         */
        fun bindAlias(alias: String?) {
            _XPush.get().bindAlias(alias)
        }

        /**
         * 解绑别名
         *
         * @param alias 别名
         */
        fun unBindAlias(alias: String?) {
            _XPush.get().unBindAlias(alias)
        }

        /**
         * 获取别名
         */
        val alias: Unit
            get() {
                _XPush.get().getAlias()
            }

        /**
         * 添加标签
         *
         * @param tag 标签
         */
        fun addTags(vararg tag: String?) {
            _XPush.get().addTags(*tag)
        }

        /**
         * 删除标签
         *
         * @param tag 标签
         */
        fun deleteTags(vararg tag: String?) {
            _XPush.get().deleteTags(*tag)
        }

        /**
         * 获取标签
         */
        val tags: Unit
            get() {
                _XPush.get().getTags()
            }

        /**
         * 获取推送令牌
         */
        val pushToken: String
            get() = _XPush.get().pushToken

        /**
         * @return 推送平台码
         */
        val platformCode: Int
            get() = _XPush.get().platformCode

        /**
         * @return 推送平台的名称
         */
        val platformName: String
            get() = _XPush.get().platformName

        /**
         * @return 推送连接状态
         */
        val connectStatus: Int
            get() = XPushManager.get().connectStatus
        //===============================IPushDispatcher========================================//
        /**
         * 设置消息推送的事件转发器
         *
         * @param iPushDispatcher 消息推送的事件转发器
         * @return
         */
        fun setIPushDispatcher(iPushDispatcher: IPushDispatcher) {
            _XPush.get().setIPushDispatcher(iPushDispatcher)
        }

        /**
         * 转发命令执行结果
         *
         * @param context
         * @param commandType 命令类型
         * @param resultCode  结果码
         * @param content     内容
         * @param extraMsg    额外信息
         * @param error       错误信息
         * @see CommandType.TYPE_ADD_TAG
         *
         * @see CommandType.TYPE_DEL_TAG
         *
         * @see CommandType.TYPE_GET_TAG
         *
         * @see CommandType.TYPE_AND_OR_DEL_TAG
         *
         * @see CommandType.TYPE_REGISTER
         *
         * @see CommandType.TYPE_UNREGISTER
         *
         * @see CommandType.TYPE_BIND_ALIAS
         *
         * @see CommandType.TYPE_UNBIND_ALIAS
         *
         * @see CommandType.TYPE_GET_ALIAS
         *
         * @see ResultCode.RESULT_ERROR
         *
         * @see ResultCode.RESULT_OK
         */
        fun transmitCommandResult(context: Context?, @CommandType commandType: Int, @ResultCode resultCode: Int, content: String?, extraMsg: String?, error: String?) {
            _XPush.get().transmitCommandResult(context, commandType, resultCode, content, extraMsg, error)
        }

        /**
         * 转发连接状态发生改变
         *
         * @param context
         * @param connectStatus 推送连接状态
         */
        fun transmitConnectStatusChanged(context: Context?, @ConnectStatus connectStatus: Int) {
            _XPush.get().transmitConnectStatusChanged(context, connectStatus)
        }

        /**
         * 转发通知信息
         *
         * @param context
         * @param notifyId 通知ID
         * @param title    通知标题
         * @param content  通知内容
         * @param extraMsg 额外消息
         */
        fun transmitNotification(context: Context?, notifyId: Int, title: String?, content: String?, extraMsg: String?, contentType: String?, keyValue: Map<String?, String?>?) {
            _XPush.get().transmitNotification(context, notifyId, title, content, extraMsg, contentType, keyValue)
        }

        /**
         * 转发通知点击事件
         *
         * @param context
         * @param notifyId 通知ID
         * @param title    通知标题
         * @param content  通知内容
         * @param extraMsg 额外消息
         */
        @JvmStatic
        fun transmitNotificationClick(context: Context?, notifyId: Int, title: String?, content: String?, extraMsg: String?, contentType: String?, keyValue: Map<String?, String?>?) {
            _XPush.get().transmitNotificationClick(context, notifyId, title, content, extraMsg, contentType, keyValue)
        }

        /**
         * 转发自定义消息
         *
         * @param context
         * @param msg      自定义消息内容
         * @param extraMsg 拓展消息
         */
        fun transmitMessage(context: Context?, msg: String?, extraMsg: String?, contentType: String?, keyValue: Map<String?, String?>?) {
            _XPush.get().transmitMessage(context, msg, extraMsg, contentType, keyValue)
        }

        /**
         * 转发自定义消息
         *
         * @param context
         * @param pushMsg 推送消息
         */
        fun transmitMessage(context: Context?, pushMsg: XPushMsg?) {
            _XPush.get().transmitMessage(context, pushMsg)
        }

        /**
         * 动态注册消息推送的接收广播【解决Android 8.0（26)之后静态广播注册失效的问题方案一，不推荐使用】
         *
         * @param pushReceiver
         */
        fun registerPushReceiver(pushReceiver: AbstractPushReceiver?) {
            val filter = IntentFilter()
            filter.addAction(PushAction.RECEIVE_CONNECT_STATUS_CHANGED)
            filter.addAction(PushAction.RECEIVE_NOTIFICATION)
            filter.addAction(PushAction.RECEIVE_NOTIFICATION_CLICK)
            filter.addAction(PushAction.RECEIVE_MESSAGE)
            filter.addAction(PushAction.RECEIVE_COMMAND_RESULT)
            filter.addCategory(context.packageName)
            context.registerReceiver(pushReceiver, filter)
        }
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}