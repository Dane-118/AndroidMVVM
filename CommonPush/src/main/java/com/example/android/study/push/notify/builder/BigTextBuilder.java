
package com.example.android.study.push.notify.builder;


import androidx.core.app.NotificationCompat;

/**
 * 多文本通知
 *
 * @author xuexiang
 * @since 2019-08-16 10:30
 */
public class BigTextBuilder extends BaseBuilder {

    @Override
    protected void beforeBuild() {
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle();
        textStyle.setBigContentTitle(mContentTitle).bigText(mContentText).setSummaryText(mSummaryText);
        setStyle(textStyle);
    }
}
