package com.example.android.study.commonres.utils

import android.annotation.SuppressLint
import android.util.Log
import com.blankj.utilcode.constant.TimeConstants
import com.blankj.utilcode.util.TimeUtils
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val calendar = Calendar.getInstance()
    val todayYear = calendar.get(Calendar.YEAR)
    val todayMonth = calendar.get(Calendar.MONTH)
    private val todayDay = calendar.get(Calendar.DAY_OF_MONTH)

    fun getMonthStartDay(year: Int, month: Int): Int {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        return calendar.get(Calendar.DAY_OF_WEEK) - 1
    }

    fun isToday(year: Int, month: Int, day: Int): Boolean {
        return year == todayYear && month == todayMonth && day == todayDay
    }

    fun getDaysInMonth(month: Int, year: Int): Int {
        return when (month) {
            Calendar.JANUARY, Calendar.MARCH, Calendar.MAY, Calendar.JULY, Calendar.AUGUST, Calendar.OCTOBER, Calendar.DECEMBER -> 31
            Calendar.APRIL, Calendar.JUNE, Calendar.SEPTEMBER, Calendar.NOVEMBER -> 30
            Calendar.FEBRUARY -> if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 29 else 28
            else -> throw IllegalArgumentException("Invalid Month")
        }
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    fun isEffectiveDate(nowTime: Date, startTime: Date, endTime: Date): Boolean {
        if (nowTime.time == startTime.time
                || nowTime.time == endTime.time) {
            return true
        }
        val date = Calendar.getInstance()
        date.time = nowTime
        val begin = Calendar.getInstance()
        begin.time = startTime
        val end = Calendar.getInstance()
        end.time = endTime
        return date.after(begin) && date.before(end)
    }

    fun getBeforeDay(day: Int): Date {
        val now = Calendar.getInstance()
        now.add(Calendar.DAY_OF_MONTH, day)
        return now.time
    }

    /**
     * 是否已过发车时间
     */
    @SuppressLint("SimpleDateFormat")
    fun getTimeSpanByNow(string: String): Int {
        val fromTime = TimeUtils.string2Millis(string, SimpleDateFormat("yyyy-MM-dd HH:mm"))
        Log.e("getTimeSpanByNow", "getTimeSpanByNow: ${fromTime}")
        return when {
            TimeUtils.getTimeSpanByNow(fromTime, TimeConstants.MIN) in 31L..60L -> {
                1
            }
            TimeUtils.getTimeSpanByNow(fromTime, TimeConstants.MIN) in 1L..30 -> {
                2
            }
            TimeUtils.getTimeSpanByNow(fromTime, TimeConstants.MIN) <= 0 -> {
                3
            }
            else ->
                4
        }

    }

}