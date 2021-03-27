
package com.example.android.study.push.notify.builder;


import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 带多条消息合并的消息盒通知
 *
 * @author xuexiang
 * @since 2019-08-16 10:30
 */
public class MailboxBuilder extends BaseBuilder {

    private List<String> mMessages;

    /**
     * 添加消息内容
     * @param msg
     * @return
     */
    public MailboxBuilder addMsg(String msg) {
        if (mMessages == null) {
            mMessages = new ArrayList<>();
        }
        mMessages.add(msg);
        return this;
    }

    /**
     * 设置消息的内容
     * @param messages
     * @return
     */
    public MailboxBuilder setMessages(List<String> messages) {
        mMessages = messages;
        return this;
    }

    @Override
    protected void beforeBuild() {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        if (mMessages != null && mMessages.size() > 0) {
            if (mMessages.size() > 1) {
                for (String msg : mMessages) {
                    inboxStyle.addLine(msg);
                }
                String text = "你收到了[" + mMessages.size() + "]条信息";
                inboxStyle.setSummaryText(text);
                setStyle(inboxStyle);
            } else if (mMessages.size() == 1) {
                setContentText(mMessages.get(0));
            }
        }
    }
}
