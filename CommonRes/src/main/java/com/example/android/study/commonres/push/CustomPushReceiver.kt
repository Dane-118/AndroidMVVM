package com.example.android.study.commonres.push

import android.content.Context
import com.example.android.study.push.core.receiver.impl.XPushReceiver
import com.example.android.study.push.entity.XPushMsg

/**

@author fangdongdong
@description:
@date : 2021/1/30 17:38
 */
class CustomPushReceiver: XPushReceiver() {



    override fun onNotificationClick(context: Context?, msg: XPushMsg?) {
        super.onNotificationClick(context, msg)

    }

}