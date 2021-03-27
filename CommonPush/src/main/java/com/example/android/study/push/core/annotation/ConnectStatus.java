
package com.example.android.study.push.core.annotation;


import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.example.android.study.push.core.annotation.ConnectStatus.CONNECTED;

/**
 * 推送连接状态
 *
 * @author xuexiang
 * @since 2019-08-17 11:01
 */
@IntDef({ConnectStatus.DISCONNECT, ConnectStatus.CONNECTING, CONNECTED})
@Retention(RetentionPolicy.SOURCE)
public @interface ConnectStatus {
    /**
     * 已断开
     */
    int DISCONNECT = 10;
    /**
     * 连接中
     */
    int CONNECTING = 11;
    /**
     * 已连接
     */
    int CONNECTED = 12;

}
