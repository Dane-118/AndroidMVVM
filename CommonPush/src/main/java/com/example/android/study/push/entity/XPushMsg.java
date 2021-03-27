package com.example.android.study.push.entity;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * 推送消息实体
 *
 * @author xuexiang
 * @since 2019-08-15 11:52
 */
public class XPushMsg implements Parcelable {

    public static final Creator<XPushMsg> CREATOR = new Creator<XPushMsg>() {
        @Override
        public XPushMsg createFromParcel(Parcel in) {
            return new XPushMsg(in);
        }

        @Override
        public XPushMsg[] newArray(int size) {
            return new XPushMsg[size];
        }
    };
    /**
     * 消息ID / 状态
     */
    private int mId;
    /**
     * 通知标题
     */
    private String mTitle;
    /**
     * 通知内容
     */
    private String mContent;
    /**
     * 自定义消息
     */
    private String mMsg;
    /**
     * 消息拓展字段
     */
    private String mExtraMsg;
    private String mContentType;
    /**
     * 消息键值对(初始化值，防止序列化出错)
     */
    private Map<String, String> mKeyValue;

    protected XPushMsg(Parcel in) {
        mId = in.readInt();
        mTitle = in.readString();
        mContent = in.readString();
        mMsg = in.readString();
        mExtraMsg = in.readString();
        mContentType = in.readString();
    }

    public XPushMsg() {

    }

    public XPushMsg(int id) {
        mId = id;
    }

    public XPushMsg(int id, String title, String content, String msg) {
        mId = id;
        mTitle = title;
        mContent = content;
        mMsg = msg;
    }

    public XPushMsg(int id, String title, String content, String msg, String extraMsg, String contentType, Map<String, String> keyValue) {
        mId = id;
        mTitle = title;
        mContent = content;
        mMsg = msg;
        mExtraMsg = extraMsg;
        mContentType = contentType;
        mKeyValue = keyValue;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mTitle);
        dest.writeString(mContent);
        dest.writeString(mMsg);
        dest.writeString(mExtraMsg);
        dest.writeString(mContentType);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getContentType() {
        return this.mContentType == null ? "" : this.mContentType;
    }

    public void setContentType(final String contentType) {
        this.mContentType = contentType;
    }

    public int getId() {
        return mId;
    }

    public XPushMsg setId(int id) {
        mId = id;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public XPushMsg setTitle(String title) {
        mTitle = title;
        return this;
    }

    public String getContent() {
        return mContent;
    }

    public XPushMsg setContent(String content) {
        mContent = content;
        return this;
    }

    public String getMsg() {
        return mMsg;
    }

    public XPushMsg setMsg(String msg) {
        mMsg = msg;
        return this;
    }

    public String getExtraMsg() {
        return mExtraMsg;
    }

    public XPushMsg setExtraMsg(String extraMsg) {
        mExtraMsg = extraMsg;
        return this;
    }

    public Map<String, String> getKeyValue() {
        return mKeyValue;
    }

    public XPushMsg setKeyValue(Map<String, String> keyValue) {
        mKeyValue = keyValue;
        return this;
    }

    /**
     * @return 转化为通知
     */
    public Notification toNotification() {
        return new Notification(mId, mTitle, mContent, mExtraMsg, mKeyValue);
    }

    /**
     * @return 转化为自定义消息
     */
    public CustomMessage toCustomMessage() {
        return new CustomMessage(mMsg, mExtraMsg, mContentType, mKeyValue);
    }


    @Override
    public String toString() {
        return "XPushMsg{" +
                "mId=" + mId +
                ", mTitle='" + mTitle + '\'' +
                ", mContent='" + mContent + '\'' +
                ", mMsg='" + mMsg + '\'' +
                ", mExtraMsg='" + mExtraMsg + '\'' +
                ", contentType='" + mContentType + '\'' +
                ", mKeyValue=" + mKeyValue +
                '}';
    }
}
