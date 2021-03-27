
package com.example.android.study.push.core.dispatcher.impl;

import android.content.Context;
import android.os.Parcelable;

import com.example.android.study.push.core.annotation.PushAction;
import com.example.android.study.push.core.dispatcher.IPushDispatcher;
import com.example.android.study.push.util.TransmitDataUtils;


/**
 * 默认的消息推送中间件
 *
 * @author xuexiang
 * @since 2019-08-16 9:21
 */
public class DefaultPushDispatcherImpl implements IPushDispatcher {

    @Override
    public void transmit(Context context, @PushAction String action, Parcelable data) {
        TransmitDataUtils.sendPushData(context, action, data);
    }
}
