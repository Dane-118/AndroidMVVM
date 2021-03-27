package com.example.android.study.push.core;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import com.example.android.study.push.XPush;
import com.example.android.study.push.util.PushUtils;


/**
 * XPush默认提供的通知推送打开指定页面的点击处理[透明activity]，使用deeplink技术
 * <p>
 * 格式如下：
 * <p>
 * xpush://com.xuexiang.xpush/notification?title=这是一个通知&content=这是通知的内容&extraMsg=xxxxxxxxx&keyValue={"param1": "1111", "param2": "2222"}
 *
 * @author xuexiang
 * @since 2019-08-23 17:59
 */
public class XPushNotificationClickActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        if (uri != null) {
            //通知标题
            String title = uri.getQueryParameter("title");
            //通知内容
            String content = uri.getQueryParameter("content");
            //通知附带拓展内容
            String extraMsg = uri.getQueryParameter("extraMsg");
            // TODO: 3/9/21 待测试
            String contentType = uri.getQueryParameter("contentType");
            //通知附带键值对
            String keyValue = uri.getQueryParameter("keyValue");
            XPush.transmitNotificationClick(this, -1, title, content, extraMsg, contentType, PushUtils.json2Map(keyValue));
        }
        finish();
    }

}
