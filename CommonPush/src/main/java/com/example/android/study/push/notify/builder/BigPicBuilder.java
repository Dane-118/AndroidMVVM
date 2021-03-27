
package com.example.android.study.push.notify.builder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.example.android.study.push.XPush;


/**
 * 附带图片的通知
 *
 * @author xuexiang
 * @since 2019-08-16 10:35
 */
public class BigPicBuilder extends BaseBuilder {

    private Bitmap mBitmap;
    /**
     * 图片的资源id
     */
    @DrawableRes
    private int mBigPicResId;


    public BigPicBuilder setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        return this;
    }


    public BigPicBuilder setPicRes(@DrawableRes int bigPicResId) {
        mBigPicResId = bigPicResId;
        return this;
    }

    @Override
    public void beforeBuild() {
        NotificationCompat.BigPictureStyle picStyle = new NotificationCompat.BigPictureStyle();
        if (mBitmap == null || mBitmap.isRecycled()) {
            if (mBigPicResId != 0) {
                mBitmap = getBitmap(mBigPicResId);
            }
        }
        picStyle.bigPicture(mBitmap);
        picStyle.setBigContentTitle(mContentTitle);
        picStyle.setSummaryText(mSummaryText);
        setStyle(picStyle);
    }


    /**
     * Return bitmap.
     *
     * @param resId The resource id.
     * @return bitmap
     */
    private static Bitmap getBitmap(@DrawableRes final int resId) {
        Drawable drawable = ContextCompat.getDrawable(XPush.getContext(), resId);
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

}
