package com.example.android.study.push.entity

/**
 * 推送通知
 *
 * @author xuexiang
 * @since 2019-08-17 14:37
 */
class Notification {
    /**
     * 消息ID / 状态
     */
    var id = 0
        private set

    /**
     * 通知标题
     */
    var title: String? = null
        private set

    /**
     * 通知内容
     */
    var content: String? = null
        private set

    /**
     * 消息拓展字段
     */
    var extraMsg: String? = null
        private set

    /**
     * 消息键值对(初始化值，防止序列化出错)
     */
    var keyValue: Map<String, String>? = null
        private set

    constructor() {}
    constructor(id: Int, title: String?, content: String?, extraMsg: String?, keyValue: Map<String, String>?) {
        this.id = id
        this.title = title
        this.content = content
        this.extraMsg = extraMsg
        this.keyValue = keyValue
    }

    fun setId(id: Int): Notification {
        this.id = id
        return this
    }

    fun setTitle(title: String?): Notification {
        this.title = title
        return this
    }

    fun setContent(content: String?): Notification {
        this.content = content
        return this
    }

    fun setExtraMsg(extraMsg: String?): Notification {
        this.extraMsg = extraMsg
        return this
    }

    fun setKeyValue(keyValue: Map<String, String>?): Notification {
        this.keyValue = keyValue
        return this
    }

    override fun toString(): String {
        return "Notification{" +
                "mId=" + id +
                ", mTitle='" + title + '\'' +
                ", mContent='" + content + '\'' +
                ", mExtraMsg='" + extraMsg + '\'' +
                ", mKeyValue=" + keyValue +
                '}'
    }
}