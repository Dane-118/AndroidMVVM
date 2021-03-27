
package com.example.android.study.push.core.annotation;


import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 推送广播动作
 *
 * @author xuexiang
 * @since 2019-08-15 18:08
 */
@StringDef({PushAction.RECEIVE_CONNECT_STATUS_CHANGED,
        PushAction.RECEIVE_NOTIFICATION,
        PushAction.RECEIVE_NOTIFICATION_CLICK,
        PushAction.RECEIVE_MESSAGE,
        PushAction.RECEIVE_COMMAND_RESULT
})
@Retention(RetentionPolicy.SOURCE)
public @interface PushAction {

    /**
     * 推送的连接状态发生改变
     */
    String RECEIVE_CONNECT_STATUS_CHANGED = "com.xuexiang.xpush.core.action.RECEIVE_CONNECT_STATUS_CHANGED";
    /**
     * 接收到通知
     */
    String RECEIVE_NOTIFICATION = "com.xuexiang.xpush.core.action.RECEIVE_NOTIFICATION";
    /**
     * 接收到通知点击
     */
    String RECEIVE_NOTIFICATION_CLICK = "com.xuexiang.xpush.core.action.RECEIVE_NOTIFICATION_CLICK";
    /**
     * 接收到自定义消息
     */
    String RECEIVE_MESSAGE = "com.xuexiang.xpush.core.action.RECEIVE_MESSAGE";
    /**
     * 接收到命令执行结果
     */
    String RECEIVE_COMMAND_RESULT = "com.xuexiang.xpush.core.action.RECEIVE_COMMAND_RESULT";

}
