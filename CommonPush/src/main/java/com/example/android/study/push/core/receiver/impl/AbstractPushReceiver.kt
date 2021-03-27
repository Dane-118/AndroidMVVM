/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.example.android.study.push.core.receiver.impl

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.example.android.study.push.core.XPushManager
import com.example.android.study.push.core.annotation.PushAction
import com.example.android.study.push.core.receiver.IPushReceiver
import com.example.android.study.push.entity.XPushCommand
import com.example.android.study.push.entity.XPushMsg
import com.example.android.study.push.logs.PushLog
import com.example.android.study.push.util.TransmitDataUtils

/**
 * 抽象的消息推送接收器
 *
 * @author xuexiang
 * @since 2019-08-15 18:04
 */
abstract class AbstractPushReceiver : BroadcastReceiver(), IPushReceiver {
    override fun onReceive(context: Context, intent: Intent) {

        val action = intent.action
        val parcelable = parsePushData<Parcelable>(intent)
        when {
            PushAction.RECEIVE_COMMAND_RESULT == action -> {
                onCommandResult(context, parcelable as XPushCommand)
            }
            PushAction.RECEIVE_NOTIFICATION == action -> {
                onNotification(context, parcelable as XPushMsg)
            }
            PushAction.RECEIVE_NOTIFICATION_CLICK == action -> {
                onNotificationClick(context, parcelable as XPushMsg)
            }
            PushAction.RECEIVE_MESSAGE == action -> {
                onMessageReceived(context, parcelable as XPushMsg)
            }
            PushAction.RECEIVE_CONNECT_STATUS_CHANGED == action -> {
                onConnectStatusChanged(context, (parcelable as XPushMsg).id)
            }
        }
        PushLog.i(String.format("%s--%s", action, parcelable.toString()))
    }

    override fun <T : Parcelable?> parsePushData(intent: Intent?): T {
        return TransmitDataUtils.parsePushData(intent)
    }

    override fun onNotification(context: Context?, msg: XPushMsg?) {
        //处理收到通知的事件
    }

    override fun onCommandResult(context: Context?, command: XPushCommand?) {
        //处理命令执行的结果
    }

    override fun onConnectStatusChanged(context: Context?, connectStatus: Int) {
        XPushManager.get().notifyConnectStatusChanged(connectStatus)
    }
}