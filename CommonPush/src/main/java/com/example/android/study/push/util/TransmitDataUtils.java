package com.example.android.study.push.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.android.study.push.core.annotation.PushAction;

/**
 * 数据传输工具
 *
 * @author xuexiang
 * @since 2019-08-16 9:00
 */
public final class TransmitDataUtils {

    private static final String INTENT_DATA_PUSH = "com.xuexiang.xpush.util.push_data";

    private TransmitDataUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 发送数据
     *
     * @param context
     * @param action  动作
     * @param data    数据
     */
    public static void sendPushData(Context context, @PushAction String action, Parcelable data) {
        Intent intent = new Intent(action);
        intent.putExtra(INTENT_DATA_PUSH, data);
        intent.addCategory(context.getPackageName());
        context.sendBroadcast(intent);
    }

    /**
     * 发送数据【解决8.0之后静态广播注册失效的问题】
     *
     * @param context
     * @param action    动作
     * @param component 广播接收器的组件
     * @param data      数据
     */
    public static void sendPushData(Context context, @PushAction String action, @NonNull ComponentName component, Parcelable data) {
        Intent intent = new Intent(action);
        intent.putExtra(INTENT_DATA_PUSH, data);
        intent.setComponent(component);
        intent.addCategory(context.getPackageName());
        context.sendBroadcast(intent);
    }

    /**
     * 解析数据
     *
     * @param intent
     * @param <T>
     * @return
     */
    public static <T extends Parcelable> T parsePushData(Intent intent) {
        return intent.getParcelableExtra(INTENT_DATA_PUSH);
    }

}
