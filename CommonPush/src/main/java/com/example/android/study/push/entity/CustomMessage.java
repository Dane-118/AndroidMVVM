package com.example.android.study.push.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;

/**
 * 自定义（透传）消息
 *
 * @author xuexiang
 * @since 2019-08-17 14:38
 */
public class CustomMessage implements Parcelable {

    public static final Creator<CustomMessage> CREATOR = new Creator<CustomMessage>() {
        @Override
        public CustomMessage createFromParcel(Parcel in) {
            return new CustomMessage(in);
        }

        @Override
        public CustomMessage[] newArray(int size) {
            return new CustomMessage[size];
        }
    };
    /**
     * 自定义消息
     */
    private String mMsg;
    /**
     * 消息拓展字段
     */
    private String mExtraMsg;
    private String mContentType;

    private String type;

    public String getType() {
        return this.type == null ? "" : this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    /**
     * 消息键值对(初始化值，防止序列化出错)
     */
    private Map<String, String> mKeyValue;

    protected CustomMessage(Parcel in) {
        mMsg = in.readString();
        mExtraMsg = in.readString();
        mContentType = in.readString();
    }

    public CustomMessage() {
    }

    public CustomMessage(String msg, String extraMsg, String contentType, Map<String, String> keyValue) {
        mMsg = msg;
        mExtraMsg = extraMsg;
        mContentType = contentType;
        mKeyValue = keyValue;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
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

    public String getMsg() {
        return mMsg;
    }

    public CustomMessage setMsg(String msg) {
        mMsg = msg;
        return this;
    }

    public String getExtraMsg() {
        return mExtraMsg;
    }

    public CustomMessage setExtraMsg(String extraMsg) {
        mExtraMsg = extraMsg;
        return this;
    }

    public Map<String, String> getKeyValue() {
        return mKeyValue;
    }

    public CustomMessage setKeyValue(Map<String, String> keyValue) {
        mKeyValue = keyValue;
        return this;
    }


}
