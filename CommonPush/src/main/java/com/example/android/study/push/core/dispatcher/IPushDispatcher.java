
package com.example.android.study.push.core.dispatcher;

import android.content.Context;
import android.os.Parcelable;

import com.example.android.study.push.core.annotation.PushAction;


/**
 * 消息推送事件转发器
 *
 * @author xuexiang
 * @since 2019-08-16 9:07
 */
public interface IPushDispatcher {

    /**
     * 转译消息
     *
     * @param context
     * @param action  动作
     * @param data    数据
     */
    void transmit(Context context, @PushAction String action, Parcelable data);

}
