
package com.example.android.study.push.notify.builder;


import androidx.core.app.NotificationCompat;

/**
 * 带进度的通知
 *
 * @author xuexiang
 * @since 2019-08-16 10:35
 */
public class ProgressBuilder extends BaseBuilder {

    private final static String DEFAULT_FORMAT = "进度:%d/%d";
    /**
     * 最大进度
     */
    private int mMax = 0;
    /**
     * 进度条
     */
    private int mProgress = 0;
    /**
     * 是否无进度条
     */
    private boolean mIndeterminate = false;

    /**
     * 模版
     */
    private String mFormat = DEFAULT_FORMAT;

    public ProgressBuilder setProgress(int max, int progress) {
        mMax = max;
        mProgress = progress;
        setContentText(String.format(DEFAULT_FORMAT, mProgress, mMax));
        return this;
    }

    public ProgressBuilder setMaxProgress(int max) {
        mMax = max;
        return this;
    }

    public ProgressBuilder setIndeterminate(boolean indeterminate) {
        mIndeterminate = indeterminate;
        if (mIndeterminate) {
            mMax = 0;
            mProgress = 0;
            setContentText(null);
        }
        return this;
    }

    public ProgressBuilder setFormat(String format) {
        mFormat = format;
        return this;
    }

    /**
     * 更新进度
     * @param progress
     * @param args
     */
    public void updateProgress(int progress, String format, Object... args) {
        mProgress = progress;
        mFormat = format;
        setContentText(String.format(mFormat, args));
    }

    /**
     * 更新进度
     * @param progress
     */
    public void updateProgress(int progress) {
        mProgress = progress;
        setContentText(String.format(DEFAULT_FORMAT, mProgress, mMax));
    }

    @Override
    protected void afterBuild() {
        updateProgress();
        getBuilder().setDefaults(0);
        getBuilder().setPriority(NotificationCompat.PRIORITY_LOW);
    }

    private void updateProgress() {
        getBuilder().setProgress(mMax, mProgress, mIndeterminate);
    }


}
