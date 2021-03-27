package com.example.android.study.push.jpush

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import cn.jpush.android.api.*
import cn.jpush.android.service.JPushMessageReceiver
import com.example.android.study.push.XPush
import com.example.android.study.push.core.annotation.CommandType
import com.example.android.study.push.core.annotation.ConnectStatus
import com.example.android.study.push.core.annotation.ResultCode
import com.example.android.study.push.logs.PushLog
import com.example.android.study.push.util.PushUtils
import org.json.JSONObject

/**
 * 消息推送接收器
 *
 * @author xuexiang
 * @since 2019-08-16 16:54
 */
class PushMessageReceiver : JPushMessageReceiver() {
    override fun onMessage(context: Context, message: CustomMessage) {
        PushLog.d(TAG + "[onMessage]:" + message)
        XPush.transmitMessage(context, message.message, message.extra, message.contentType, null)
    }

    override fun onNotifyMessageOpened(context: Context, message: NotificationMessage) {
        PushLog.d(TAG + "[onNotifyMessageOpened]:" + message)
        try {
            XPush.transmitNotificationClick(context, message.notificationId, message.notificationTitle,
                    message.notificationContent, null,
                    null,
                    PushUtils.toMap(JSONObject(message.notificationExtras)))
        } catch (e: Exception) {
            e.printStackTrace()
            XPush.transmitNotificationClick(context,
                    message.notificationId,
                    message.notificationTitle,
                    message.notificationContent,
                    message.notificationExtras,
                    null,
                    null)
        }
    }

    override fun onMultiActionClicked(context: Context, intent: Intent) {
        if (intent.extras == null) {
            return
        }
        PushLog.d(TAG + "[onMultiActionClicked] 用户点击了通知栏按钮:" + intent.extras!!.getString(JPushInterface.EXTRA_NOTIFICATION_ACTION_EXTRA))
    }

    override fun onNotifyMessageArrived(context: Context, message: NotificationMessage) {
        PushLog.d(TAG + "[onNotifyMessageArrived]:" + message)
        try {
            XPush.transmitNotification(context,
                    message.notificationId,
                    message.notificationTitle,
                    message.notificationContent,
                    null,
                    null,
                    PushUtils.toMap(JSONObject(message.notificationExtras)))
        } catch (e: Exception) {
            e.printStackTrace()
            XPush.transmitNotification(context,
                    message.notificationId,
                    message.notificationTitle,
                    message.notificationContent,
                    message.notificationExtras,
                    null,
                    null)
        }
    }

    override fun onNotifyMessageDismiss(context: Context, message: NotificationMessage) {
        PushLog.d(TAG + "[onNotifyMessageDismiss]:" + message)
    }

    override fun onRegister(context: Context, registrationId: String) {
        PushLog.d(TAG + "[onRegister]:" + registrationId)
        XPush.transmitCommandResult(context, CommandType.TYPE_REGISTER,
                if (TextUtils.isEmpty(registrationId)) ResultCode.RESULT_ERROR else ResultCode.RESULT_OK,
                registrationId, null, null)
    }

    override fun onConnected(context: Context, isConnected: Boolean) {
        PushLog.d(TAG + "[onConnected] " + isConnected)
        XPush.transmitConnectStatusChanged(context, if (isConnected) ConnectStatus.CONNECTED else ConnectStatus.DISCONNECT)
    }

    override fun onCommandResult(context: Context, cmdMessage: CmdMessage) {
        PushLog.d(TAG + "[onCommandResult] " + cmdMessage)
    }

    override fun onTagOperatorResult(context: Context, jPushMessage: JPushMessage) {
        XPush.transmitCommandResult(context, jPushMessage.sequence,
                if (jPushMessage.errorCode == 0) ResultCode.RESULT_OK else jPushMessage.errorCode,
                PushUtils.collection2String(jPushMessage.tags), null, null)
        super.onTagOperatorResult(context, jPushMessage)
    }

    override fun onAliasOperatorResult(context: Context, jPushMessage: JPushMessage) {
        XPush.transmitCommandResult(context, jPushMessage.sequence,
                if (jPushMessage.errorCode == 0) ResultCode.RESULT_OK else jPushMessage.errorCode,
                jPushMessage.alias, null, null)
        super.onAliasOperatorResult(context, jPushMessage)
    }

    override fun onCheckTagOperatorResult(context: Context, jPushMessage: JPushMessage) {
        super.onCheckTagOperatorResult(context, jPushMessage)
    }

    override fun onMobileNumberOperatorResult(context: Context, jPushMessage: JPushMessage) {
        super.onMobileNumberOperatorResult(context, jPushMessage)
    }

    companion object {
        const val TAG = "JPush-"
    }
}