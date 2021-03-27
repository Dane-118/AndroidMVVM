
package com.example.android.study.push.core.dispatcher.impl;

import android.content.ComponentName;
import android.content.Context;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.android.study.push.XPush;
import com.example.android.study.push.core.dispatcher.IPushDispatcher;
import com.example.android.study.push.core.receiver.impl.AbstractPushReceiver;
import com.example.android.study.push.util.TransmitDataUtils;


/**
 * 解决Android 8.0（26)之后静态广播注册失效的问题方案二，推荐使用
 *
 * @author xuexiang
 * @since 2019-08-24 23:53
 */
public class Android26PushDispatcherImpl implements IPushDispatcher {

    private ComponentName mComponent;

    public Android26PushDispatcherImpl(@NonNull Class<? extends AbstractPushReceiver> cls) {
        mComponent = new ComponentName(XPush.getContext(), cls);
    }

    @Override
    public void transmit(Context context, String action, Parcelable data) {
        TransmitDataUtils.sendPushData(context, action, mComponent, data);
    }
}
