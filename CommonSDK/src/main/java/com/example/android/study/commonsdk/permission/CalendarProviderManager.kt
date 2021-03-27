package com.example.android.study.commonsdk.permission

import android.Manifest
import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import com.tbruyelle.rxpermissions3.RxPermissions
import java.util.*

/**
 * @author fangdongdong
 * @description:系统日历工具
 * @date : 2020/11/6 14:15
 */
object CalendarProviderManager {
    // ----------------------- 创建日历账户时账户名使用 ---------------------------
    private const val CALENDAR_NAME = "Gaolv"
    private const val CALENDAR_ACCOUNT_NAME = "GaolvZongHeng"
    private const val CALENDAR_DISPLAY_NAME = "GaolvZongHeng的账户"

    val perms = arrayOf(
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR
    )


    fun checkPermission(fragment: Fragment, permissionSuccess: (() -> Unit)? = null) {
        PermissionUtil.requestPermission(object : PermissionUtil.RequestPermission {
            override fun onRequestPermissionSuccess() {
                permissionSuccess?.invoke()
            }

            override fun onRequestPermissionFailure(permissions: List<String>) {
                onRequestPermissionFailure(permissions)
            }

            override fun onRequestPermissionFailureWithAskNeverAgain(permissions: List<String>) {
                onRequestPermissionFailureWithAskNeverAgain(permissions)
            }
        }, RxPermissions(fragment), *perms)
    }

    /**
     * 获取日历账户ID(若没有则会自动创建一个)
     *
     * @return success: 日历账户ID  failed : -1  permission deny : -2
     */
    fun obtainCalendarAccountID(context: Context): Long {
        val calID = checkCalendarAccount(context)
        return if (calID >= 0) {
            calID
        } else {
            createCalendarAccount(context)
        }
    }

    /**
     * 检查是否存在日历账户
     *
     * @return 存在：日历账户ID  不存在：-1
     */
    private fun checkCalendarAccount(context: Context): Long {
        context.contentResolver.query(CalendarContract.Calendars.CONTENT_URI,
                null, null, null, null).use { cursor ->
            // 不存在日历账户
            if (null == cursor) {
                return -1
            }
            val count = cursor.count
            // 存在日历账户，获取第一个账户的ID
            return if (count > 0) {
                cursor.moveToFirst()
                cursor.getInt(cursor.getColumnIndex(CalendarContract.Calendars._ID)).toLong()
            } else {
                -1
            }
        }
    }

    /**
     * 创建一个新的日历账户
     *
     * @return success：ACCOUNT ID , create failed：-1 , permission deny：-2
     */
    private fun createCalendarAccount(context: Context): Long {
        // 系统日历表
        var uri = CalendarContract.Calendars.CONTENT_URI

        // 要创建的账户
        val accountUri: Uri?

        // 开始组装账户数据
        val account = ContentValues()
        // 账户类型：本地
        // 在添加账户时，如果账户类型不存在系统中，则可能该新增记录会被标记为脏数据而被删除
        // 设置为ACCOUNT_TYPE_LOCAL可以保证在不存在账户类型时，该新增数据不会被删除
        account.put(CalendarContract.Calendars.ACCOUNT_TYPE, CalendarContract.ACCOUNT_TYPE_LOCAL)
        // 日历在表中的名称
        account.put(CalendarContract.Calendars.NAME, CALENDAR_NAME)
        // 日历账户的名称
        account.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDAR_ACCOUNT_NAME)
        // 账户显示的名称
        account.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDAR_DISPLAY_NAME)
        // 日历的颜色
        account.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.parseColor("#515bd4"))
        // 用户对此日历的获取使用权限等级
        account.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER)
        // 设置此日历可见
        account.put(CalendarContract.Calendars.VISIBLE, 1)
        // 日历时区
        account.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, TimeZone.getDefault().id)
        // 可以修改日历时区
        account.put(CalendarContract.Calendars.CAN_MODIFY_TIME_ZONE, 1)
        // 同步此日历到设备上
        account.put(CalendarContract.Calendars.SYNC_EVENTS, 1)
        // 拥有者的账户
        account.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDAR_ACCOUNT_NAME)
        // 可以响应事件
        account.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 1)
        // 单个事件设置的最大的提醒数
        account.put(CalendarContract.Calendars.MAX_REMINDERS, 8)
        // 设置允许提醒的方式
        account.put(CalendarContract.Calendars.ALLOWED_REMINDERS, "0,1,2,3,4")
        // 设置日历支持的可用性类型
        account.put(CalendarContract.Calendars.ALLOWED_AVAILABILITY, "0,1,2")
        // 设置日历允许的出席者类型
        account.put(CalendarContract.Calendars.ALLOWED_ATTENDEE_TYPES, "0,1,2")

        /*
            TIP: 修改或添加ACCOUNT_NAME只能由SYNC_ADAPTER调用
            对uri设置CalendarContract.CALLER_IS_SYNCADAPTER为true,即标记当前操作为SYNC_ADAPTER操作
            在设置CalendarContract.CALLER_IS_SYNCADAPTER为true时,必须带上参数ACCOUNT_NAME和ACCOUNT_TYPE(任意)
         */uri = uri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDAR_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE,
                        CalendarContract.Calendars.CALENDAR_LOCATION)
                .build()
        accountUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 检查日历权限
            if (PackageManager.PERMISSION_GRANTED == context.checkSelfPermission(
                            "android.permission.WRITE_CALENDAR")) {
                context.contentResolver.insert(uri, account)
            } else {
                return -2
            }
        } else {
            context.contentResolver.insert(uri, account)
        }
        return if (accountUri == null) -1 else ContentUris.parseId(accountUri)
    }
    // ------------------------------- 添加日历事件 -----------------------------------
    /**
     * 添加日历事件
     */
    suspend fun addCalendarEvent(context: Context, title: String?, description: String?, startTime: Long, endTime: Long, previousDate: Int): Int {
        /*
            TIP: 插入一个新事件的规则：
             1.  必须包含CALENDAR_ID和DTSTART字段
             2.  必须包含EVENT_TIMEZONE字段,使用TimeZone.getDefault().getID()方法获取默认时区
             3.  对于非重复发生的事件,必须包含DTEND字段
             4.  对重复发生的事件,必须包含一个附加了RRULE或RDATE字段的DURATION字段
         */
        checkContextNull(context)

        // 获取日历账户ID，也就是要将事件插入到的账户
        val calID = obtainCalendarAccountID(context)

        // 系统日历事件表
        val uri1 = CalendarContract.Events.CONTENT_URI
        // 创建的日历事件
        val eventUri: Uri?

        // 系统日历事件提醒表
        val uri2 = CalendarContract.Reminders.CONTENT_URI
        // 创建的日历事件提醒
        val reminderUri: Uri?

        //添加日历事件
        val mCalendar = Calendar.getInstance()
        mCalendar.timeInMillis = startTime //设置开始时间
        val start = mCalendar.time.time
        mCalendar.timeInMillis = endTime //设置终止时间，开始时间加10分钟
        val end = mCalendar.time.time

        // 开始组装事件数据
        val event = ContentValues()
        // 事件要插入到的日历账户
        event.put(CalendarContract.Events.CALENDAR_ID, calID)
        // 事件开始时间
        event.put(CalendarContract.Events.DTSTART, start)
        // 事件结束时间
        event.put(CalendarContract.Events.DTEND, end)
        // 事件标题
        event.put(CalendarContract.Events.TITLE, title)
        // 事件描述(对应手机系统日历备注栏)
        event.put(CalendarContract.Events.DESCRIPTION, description)
        // 事件地点
        event.put(CalendarContract.Events.EVENT_LOCATION, "")
        // 事件时区
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        // 定义事件的显示，默认即可
        event.put(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_DEFAULT)
        // 事件的状态
        event.put(CalendarContract.Events.STATUS, 0)
        // 设置事件提醒警报可用
        event.put(CalendarContract.Events.HAS_ALARM, 1)
        // 设置事件忙
        event.put(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
        eventUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 判断权限
            if (PackageManager.PERMISSION_GRANTED == context.checkSelfPermission(
                            "android.permission.WRITE_CALENDAR")) {
                context.contentResolver.insert(uri1, event)
            } else {
                return -2
            }
        } else {
            context.contentResolver.insert(uri1, event)
        }
        if (null == eventUri) {
            return -1
        }
        if (-2 != previousDate) {
            // 获取事件ID
            val eventID = ContentUris.parseId(eventUri)

            // 开始组装事件提醒数据
            val reminders = ContentValues()
            // 此提醒所对应的事件ID
            reminders.put(CalendarContract.Reminders.EVENT_ID, eventID)
            // 设置提醒提前的时间(0：准时  -1：使用系统默认)
            reminders.put(CalendarContract.Reminders.MINUTES, previousDate)
            // 设置事件提醒方式为通知警报
            reminders.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
            reminderUri = context.contentResolver.insert(uri2, reminders)
            if (null == reminderUri) {
                return -1
            }
        }
        return 0
    }

    /**
     * 判断日历账户中是否已经存在此事件
     *
     * @param begin 事件开始时间
     * @param end   事件结束时间
     * @param title 事件标题
     */
    fun isEventAlreadyExist(context: Context, begin: Long, end: Long, title: String): Boolean {
        val projection = arrayOf(
                CalendarContract.Instances.BEGIN,
                CalendarContract.Instances.END,
                CalendarContract.Instances.TITLE
        )
        val cursor = CalendarContract.Instances.query(
                context.contentResolver, projection, begin, end, title)
        return (null != cursor && cursor.moveToFirst()
                && cursor.getString(
                cursor.getColumnIndex(CalendarContract.Instances.TITLE)) == title)
    }

    /**
     * check null
     */
    fun checkContextNull(context: Context?) {
        requireNotNull(context) { "context can not be null" }
    }
}