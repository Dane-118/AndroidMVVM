package com.example.android.study.commonres.utils

import com.blankj.utilcode.util.TimeUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    fun formatTime(date: String?): String {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        var result = ""
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val date2 = formater.parse(date)
            val format = SimpleDateFormat("yyyy年MM月dd日")
            result = format.format(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }

    fun formatTime2(date: String?): String {
        // 输入格式2019-01-01，输出格式2019年01月01日
        var result = ""
        val formater = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date2 = formater.parse(date)
            val format = SimpleDateFormat("yyyy年MM月dd日")
            result = format.format(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }

    fun formatTime3(date: String?): String {
        // 输入格式2019-01-01 00:00:00，输出格式2019.01.01 00:00:00
        var result = ""
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val date2 = formater.parse(date)
            val format = SimpleDateFormat("yyyy.MM.dd HH:mm:ss")
            result = format.format(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }

    fun formatTime4(date: String?): String {
        // 输入格式2019-01-01 00:00:00，输出格式2019.01.01 00:00:00
        var result = ""
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val date2 = formater.parse(date)
            val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            result = format.format(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }

    fun formatTime5(date: String?): String {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        var result = ""
        val formater = SimpleDateFormat("yyyy-MM-dd")
        try {
            val date2 = formater.parse(date)
            val format = SimpleDateFormat("MM月dd日")
            result = format.format(date2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }

    fun formatTimeToDate(date: String?): Date? {
        // 输入格式2019-01-01，输出格式2019年01月01日
        val formater = SimpleDateFormat("yyyy-MM-dd")
        var date2: Date? = null
        try {
            date2 = formater.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date2
    }

    fun formatTimeToDate2(date: String?): Date? {
        // 输入格式2019-01-01，输出格式2019年01月01日
        val formater = SimpleDateFormat("HH:mm")
        var date2: Date? = null
        try {
            date2 = formater.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date2
    }

    // a integer to xx:xx:xx
    fun secToTime(time: Int): String {
        var timeStr: String? = null
        var hour = 0
        var minute = 0
        var second = 0
        if (time <= 0) return "00:00" else {
            minute = time / 60
            if (minute < 60) {
                second = time % 60
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒"
            } else {
                hour = minute / 60
                if (hour > 99) return "99:59:59"
                minute = minute % 60
                second = time - hour * 3600 - minute * 60
                timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分" + unitFormat(second) + "秒"
            }
        }
        return timeStr
    }

    fun unitFormat(i: Int): String {
        var retStr: String? = null
        retStr = if (i >= 0 && i < 10) "0" + Integer.toString(i) else "" + i
        return retStr
    }

    /**
     * 比较两个时间大小
     *
     * @param date1
     * @param date2
     * @return >0：date1>date2; 0:date1=date2; <0:date1<date2></date2>
     */
    fun compareTime(date1: String?, date2: String?): Int {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        var result = 0
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val c1 = Calendar.getInstance()
        val c2 = Calendar.getInstance()
        try {
            val d1 = formater.parse(date1)
            val d2 = formater.parse(date2)
            c1.time = d1
            c2.time = d1
            result = c1.compareTo(c2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 格式化date
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    fun formatTimeToString(date: Date?): String {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        var result = ""
        val formater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            result = formater.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    /**
     * 获取指定日后 后 dayAddNum 天的 日期
     *
     * @param day 日期，格式为String："2013-9-3";
     * @param dayAddNum 增加天数 格式为int;
     * @return
     */
    fun getDateFromStr(day: String?, dayAddNum: Int): Date {
        val df = SimpleDateFormat("yyyy-MM-dd")
        var nowDate: Date? = null
        try {
            nowDate = df.parse(day)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Date(nowDate!!.time + dayAddNum * 24 * 60 * 60 * 1000)
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // String dateOk = simpleDateFormat.format(newDate2);
        // return dateOk;
    }

    /**
     * 获取指定日期 前 dayAddNum 天的 日期
     *
     * @param nowDate 日期，格式为String："2013-9-3";
     * @param dayAddNum 增加天数 格式为int;
     * @return
     */
    fun getYesterdayDate(nowDate: Date, dayAddNum: Int): Date {
        return Date(nowDate.time - dayAddNum * 24 * 60 * 60 * 1000)
    }

    /**
     * 获取指定日后 后 dayAddNum 天的 日期
     *
     * @param day 日期，格式为String："2013-9-3";
     * @return
     */
    fun getDateFromStrByCalendar(day: String?): Long {
        val df = SimpleDateFormat("yyyy-MM-dd")
        val gc = GregorianCalendar()
        var nowDate: Date? = null
        try {
            nowDate = df.parse(day)
            gc.time = nowDate
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return gc.timeInMillis
    }

    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    fun formatDateTimeToStringChinese(date: Date?): String? {
        val df = SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")
        var newDate: String? = null
        try {
            newDate = df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    /**
     * yyyy年MM月dd日 HH时mm分
     */
    fun formatDateTimeToStringChinese2(date: Date?): String? {
        val df = SimpleDateFormat("yyyy年MM月dd日 HH时mm分")
        var newDate: String? = null
        try {
            newDate = df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    /**
     * 格式化date "yyyy-MM-dd"
     */
    fun formatDateToStringChinese(date: Date?): String? {
        val df = SimpleDateFormat("yyyy年MM月dd日")
        var newDate: String? = null
        try {
            newDate = df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    /**
     * 格式化date "MM月dd日"
     */
    fun formatDateToMonthDayStringChinese(date: Date?): String? {
        val df = SimpleDateFormat("MM月dd日")
        var newDate: String? = null
        try {
            newDate = df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    /**
     * 格式化date "yyyy-MM-dd"
     */
    fun formatDateToString(date: Date?): String? {
        val df = SimpleDateFormat("yyyy-MM-dd")
        var newDate: String? = null
        try {
            newDate = df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    /**
     * 格式化date "HH:mm"
     */
    fun formatDateToTimeString(date: Date?): String? {
        val df = SimpleDateFormat("HH:mm")
        var newDate: String? = null
        try {
            newDate = df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    /**
     * 格式化date "yyyy-MM-dd"
     */
    fun formatDateToStringOnlyMonth(date: Date?): String? {
        val df = SimpleDateFormat("yyyy-MM")
        var newDate: String? = null
        try {
            newDate = df.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return newDate
    }

    /**
     * 获取年
     *
     * @return
     */
    val year: Int
        get() {
            val cd = Calendar.getInstance()
            return cd[Calendar.YEAR]
        }

    /**
     * 获取月
     *
     * @return
     */
    val month: Int
        get() {
            val cd = Calendar.getInstance()
            return cd[Calendar.MONTH] + 1
        }

    /**
     * 获取周
     *
     * @return
     */
    val weekOfYear: Int
        get() {
            val cd = Calendar.getInstance()
            return cd[Calendar.WEEK_OF_YEAR]
        }

    /**
     * 获取日
     *
     * @return
     */
    val day: Int
        get() {
            val cd = Calendar.getInstance()
            return cd[Calendar.DATE]
        }

    /**
     * 获取时
     *
     * @return
     */
    val hour: Int
        get() {
            val cd = Calendar.getInstance()
            return cd[Calendar.HOUR]
        }

    /**
     * 获取时 24小时制
     *
     * @return
     */
    val hourOfDay: Int
        get() {
            val cd = Calendar.getInstance()
            return cd[Calendar.HOUR_OF_DAY]
        }

    /**
     * 获取分
     *
     * @return
     */
    val minute: Int
        get() {
            val cd = Calendar.getInstance()
            return cd[Calendar.MINUTE]
        }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     */
    val currentTimeMillis: Long
        get() = System.currentTimeMillis()

    /**
     * 获取年
     *
     * @return
     */
    fun getYearByDate(date: Date?): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar[Calendar.YEAR]
    }

    /**
     * 获取月
     *
     * @return
     */
    fun getMonthByDate(date: Date?): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar[Calendar.MONTH] + 1
    }

    /**
     * 获取日
     *
     * @return
     */
    fun getDayByDate(date: Date?): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar[Calendar.DATE]
    }

    /**
     * 返回昨天
     *
     * @param today
     * @return
     */
    fun yesterday(today: Date?): Date {
        val calendar = Calendar.getInstance()
        calendar.time = today
        calendar[Calendar.DATE] = calendar[Calendar.DATE] - 1
        return calendar.time
    }

    /**
     * 返回明天
     *
     * @param today
     * @return
     */
    fun tomorrow(today: Date?): Date {
        val calendar = Calendar.getInstance()
        calendar.time = today
        calendar[Calendar.DATE] = calendar[Calendar.DATE] + 1
        return calendar.time
    }

    /**
     * 返回是不是昨天
     *
     * @param selectDate
     * @return
     */
    fun isYesterday(selectDate: Date?): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar[Calendar.DATE] = calendar[Calendar.DATE] - 1
        val calendar2 = Calendar.getInstance()
        calendar2.time = selectDate
        return if (calendar[Calendar.YEAR] == calendar2[Calendar.YEAR]
                && calendar[Calendar.MONTH] == calendar2[Calendar.MONTH]
                && calendar[Calendar.DATE] == calendar2[Calendar.DATE]) {
            true
        } else false
    }

    /**
     * 返回是不是今天
     *
     * @param selectDate
     * @return
     */
    fun isToday(selectDate: Date?): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val calendar2 = Calendar.getInstance()
        calendar2.time = selectDate
        return if (calendar[Calendar.YEAR] == calendar2[Calendar.YEAR]
                && calendar[Calendar.MONTH] == calendar2[Calendar.MONTH]
                && calendar[Calendar.DATE] == calendar2[Calendar.DATE]) {
            true
        } else false
    }

    /**
     * 返回上个月
     *
     * @return
     */
    val lastMonth: Date
        get() {
            val calendar = Calendar.getInstance()
            calendar.time = Date()
            calendar[Calendar.MONTH] = calendar[Calendar.MONTH] - 1
            return calendar.time
        }

    /**
     * 返回是不是上个月
     *
     * @param selectDate
     * @return
     */
    fun isLastMonth(selectDate: Date?): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar[Calendar.MONTH] = calendar[Calendar.MONTH] - 1
        val calendar2 = Calendar.getInstance()
        calendar2.time = selectDate
        return if (calendar[Calendar.YEAR] == calendar2[Calendar.YEAR]
                && calendar[Calendar.MONTH] == calendar2[Calendar.MONTH]) {
            true
        } else false
    }

    /**
     * 返回是不是本月
     *
     * @param selectDate
     * @return
     */
    fun isCurrentMonth(selectDate: Date?): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val calendar2 = Calendar.getInstance()
        calendar2.time = selectDate
        return if (calendar[Calendar.YEAR] == calendar2[Calendar.YEAR]
                && calendar[Calendar.MONTH] == calendar2[Calendar.MONTH]) {
            true
        } else false
    }

    /**
     * 返回是不是下个月
     *
     * @param selectDate
     * @return
     */
    fun isNextMonth(selectDate: Date?): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        calendar[Calendar.MONTH] = calendar[Calendar.MONTH] + 1
        val calendar2 = Calendar.getInstance()
        calendar2.time = selectDate
        return if (calendar[Calendar.YEAR] == calendar2[Calendar.YEAR]
                && calendar[Calendar.MONTH] == calendar2[Calendar.MONTH]) {
            true
        } else false
    }

    fun getWeekOfYear(dateStr: String?, dateFormat: String?): String? {
        val format = SimpleDateFormat(dateFormat)
        return try {
            val date = format.parse(dateStr)
            val calendar = Calendar.getInstance()
            calendar.firstDayOfWeek = Calendar.MONDAY
            calendar.time = date
            calendar[Calendar.YEAR].toString() + "第" + calendar[Calendar.WEEK_OF_YEAR] + "周"
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun getStartDayOfWeekNo(year: Int, weekNo: Int): String {
        val cal = getCalendarFormYear(year)
        cal[Calendar.WEEK_OF_YEAR] = weekNo
        return cal[Calendar.YEAR].toString() + "-" + (cal[Calendar.MONTH] + 1) + "-" +
                cal[Calendar.DAY_OF_MONTH]
    }

    /**
     * get the end day of given week no of a year.
     *
     * @param year
     * @param weekNo
     * @return
     */
    fun getEndDayOfWeekNo(year: Int, weekNo: Int): String {
        val cal = getCalendarFormYear(year)
        cal[Calendar.WEEK_OF_YEAR] = weekNo
        cal.add(Calendar.DAY_OF_WEEK, 6)
        return cal[Calendar.YEAR].toString() + "-" + (cal[Calendar.MONTH] + 1) + "-" +
                cal[Calendar.DAY_OF_MONTH]
    }

    private fun getCalendarFormYear(year: Int): Calendar {
        val cal = Calendar.getInstance()
        // 设置周一为一周的第一天
        cal.firstDayOfWeek = Calendar.MONDAY
        cal[Calendar.DAY_OF_WEEK] = Calendar.MONDAY
        cal[Calendar.YEAR] = year
        return cal
    }

    fun getWeekOfYearString(date: Date?): String {
        return try {
            val calendar = Calendar.getInstance()
            calendar.firstDayOfWeek = Calendar.MONDAY
            calendar.time = date
            calendar[Calendar.YEAR].toString() + "第" + calendar[Calendar.WEEK_OF_YEAR] + "周"
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    /**
     * 获取当前日期是星期几<br></br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    fun getWeekOfDate(date: Date?): String {
        val weekDays = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
        val cal = Calendar.getInstance()
        cal.time = date
        var w = cal[Calendar.DAY_OF_WEEK] - 1
        if (w < 0) w = 0
        return weekDays[w]
    }

    /**
     * 获取当前日期是星期几<br></br>
     *
     * @param dateString
     * @return 当前日期是星期几
     */
    fun getWeekOfDate(dateString: String?): String {
        val date = formatTimeToDate(dateString)
        val weekDays = arrayOf("周日", "周一", "周二", "周三", "周四", "周五", "周六")
        val cal = Calendar.getInstance()
        cal.time = date
        var w = cal[Calendar.DAY_OF_WEEK] - 1
        if (w < 0) w = 0
        return weekDays[w]
    }

    fun getDistanceOfTwoDate(before: Date, after: Date): Double {
        val beforeTime = before.time
        val afterTime = after.time
        return ((afterTime - beforeTime) / (1000 * 60 * 60 * 24)).toDouble()
    }

    /**
     * @param
     * @return true 大于
     * @description 年月日日否大于当前的年月日
     * @time 2020/4/27 14:34
     */
    fun compareToCurrentData(date: String?): Boolean {
        var flag = true
        val sdf = SimpleDateFormat("yyyy-MM-dd") // 创建日期转换对象：年 月 日
        val today = Date() // 今天 实际日期是 2018-11-12 Debug：Wed Nov 12 12:08:12 CST 2018
        try {
            val dateD = sdf.parse(date) // 将字符串转换为 date 类型 Debug：Sun Nov 11 00:00:00 CST 2018
            flag = dateD.time >= today.time
            // System.err.println("flag = "+flag); // flag = false
        } catch (e1: ParseException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }
        return flag
    }

    /**
     * @param
     * @return true 大于
     * @description 年月日日时分秒是否大于当前的年月日时分秒
     * @time 2020/4/27 14:34
     */
    fun compareToCurrentDataSecond(date: String?): Boolean {
        var flag = true
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // 创建日期转换对象：年 月 日
        val today = Date() // 今天 实际日期是 2018-11-12 Debug：Wed Nov 12 12:08:12 CST 2018
        try {
            val dateD = sdf.parse(date) // 将字符串转换为 date 类型 Debug：Sun Nov 11 00:00:00 CST 2018
            flag = dateD.time >= today.time
            // System.err.println("flag = "+flag); // flag = false
        } catch (e1: ParseException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }
        return flag
    }

    /**
     * @param
     * @return true 大于
     * @description 年月日日时分秒是否大于当前的年月日时分秒
     * @time 2020/4/27 14:34
     */
    fun compareToDataSecond(startDate: String?, endDate: String?): Boolean {
        var flag = true
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss") // 创建日期转换对象：年 月 日
        try {
            val dateS = sdf.parse(startDate) // 将字符串转换为 date 类型 Debug：Sun Nov 11 00:00:00 CST 2018
            val dateE = sdf.parse(endDate)
            flag = dateS.time >= dateE.time
            // System.err.println("flag = "+flag); // flag = false
        } catch (e1: ParseException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }
        return flag
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smallDate 较小的时间
     * @param bigDate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    fun daysBetween(smallDate: Date, bigDate: Date): Long {
        var smallDate = smallDate
        var bigDate = bigDate
        var day: Long = 0
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            smallDate = sdf.parse(sdf.format(smallDate))
            bigDate = sdf.parse(sdf.format(bigDate))
            val cal = Calendar.getInstance()
            cal.time = smallDate
            val time1 = cal.timeInMillis
            cal.time = bigDate
            val time2 = cal.timeInMillis
            val between_days = (time2 - time1) / (1000 * 3600 * 24)
            day = between_days.toString().toLong()
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return day
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smallDate 较小的时间
     * @param bigDate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    fun hoursBetween(smallDate: Date?, bigDate: Date?): Int {
        var smallDate = smallDate
        var bigDate = bigDate
        var hours = 0
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            smallDate = sdf.parse(sdf.format(smallDate))
            bigDate = sdf.parse(sdf.format(bigDate))
            val cal = Calendar.getInstance()
            cal.time = smallDate
            val time1 = cal.timeInMillis
            cal.time = bigDate
            val time2 = cal.timeInMillis
            val between_hours = (time2 - time1) / (1000 * 3600)
            hours = between_hours.toString().toInt()
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return hours
    }

    /**
     * 计算两个日期之间相差的分钟
     *
     * @param smallDate 较小的时间
     * @param bigDate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    fun minuteBetween(smallDate: Date?, bigDate: Date?): Int {
        var smallDate = smallDate
        var bigDate = bigDate
        var minute = 0
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            smallDate = sdf.parse(sdf.format(smallDate))
            bigDate = sdf.parse(sdf.format(bigDate))
            val cal = Calendar.getInstance()
            cal.time = smallDate
            val time1 = cal.timeInMillis
            cal.time = bigDate
            val time2 = cal.timeInMillis
            val between_minute = (time2 - time1) / (1000 * 60)
            minute = between_minute.toString().toInt()
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return minute
    }
    // public static ArrayList<DateBean> getTotalDateListByWeek() {
    // ArrayList<DateBean> resultList = new ArrayList<>();
    // int weekNo = getWeekOfYear();
    // if (weekNo > 0) {
    // for (int i=weekNo; i>0; i--) {
    // String result = "第" + i + "周" +" "+ getStartDayOfWeekNo(getYear(),i) +"到"+ getEndDayOfWeekNo(getYear(), i);
    // DateBean dateBean = new DateBean(i , result);
    // resultList.add(dateBean);
    // }
    // }
    // return resultList;
    // }
    /**
     * @param
     * @return
     * @description 加一个小时
     * @time 2020/4/17 17:06
     */
    fun addDate(day: String?, x: Int): String {
        val format = SimpleDateFormat("yyyy-MM-dd HH") // 24小时制
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        var date: Date? = null
        try {
            date = format.parse(day)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        if (date == null) return ""
        var cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.HOUR_OF_DAY, x) // 24小时制
        // cal.add(Calendar.HOUR, x);12小时制
        date = cal.time
        println("front:$date")
        cal = null
        return format.format(date)
    }

    /**
     * @param
     * @return
     * @description 加一天
     * @time 2020/4/17 17:06
     */
    fun addDateByDay(day: String?, x: Int): String {
        val format = SimpleDateFormat("yyyy-MM-dd") // 24小时制
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        var date: Date? = null
        try {
            date = format.parse(day)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        if (date == null) return ""
        var cal = Calendar.getInstance()
        cal.time = date
        cal.add(Calendar.DAY_OF_MONTH, x) // 24小时制
        // cal.add(Calendar.HOUR, x);12小时制
        date = cal.time
        println("front:$date")
        cal = null
        return format.format(date)
    }

    /**
     * 判断时间是否在时间段内
     *  
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    fun belongCalendar(nowTime: Date, beginTime: Date?, endTime: Date?): Boolean {
        val date = Calendar.getInstance()
        date.time = nowTime
        val begin = Calendar.getInstance()
        begin.time = beginTime
        val end = Calendar.getInstance()
        end.time = endTime
        return if (date.after(begin) && date.before(end)) {
            true
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            true
        } else {
            if (date[Calendar.YEAR] == begin[Calendar.YEAR]
                    && date[Calendar.MONTH] == begin[Calendar.MONTH]
                    && date[Calendar.DATE] == begin[Calendar.DATE]) {
                true
            } else if (date[Calendar.YEAR] == end[Calendar.YEAR]
                    && date[Calendar.MONTH] == end[Calendar.MONTH]
                    && date[Calendar.DATE] == end[Calendar.DATE]) {
                true
            } else {
                false
            }
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @return
     */
    fun isInRangeDate(date: Date, startDay: Int, endDay: Int): Boolean {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, startDay - 1)
        val calendar2 = Calendar.getInstance()
        calendar2.add(Calendar.DAY_OF_YEAR, endDay)
        return belongCalendar(date, calendar.time, calendar2.time)
    }

    // 分钟转小时
    fun turnDayHourMinuteString(minute: Long): String? {
        // 如果传入的分钟是0，默认24小时
        if (0L == minute) {
            return 24.toString() + "时"
        }
        // 如果分钟小于60，默认返回分钟
        if (0 < minute && minute < 60) {
            return minute.toString() + "分"
        }
        // 如果分钟小于24小时（1440分钟），返回小时和分钟
        if (60 <= minute && minute < 1440) {
            return if (minute % 60 == 0L) {
                val h = minute / 60
                h.toString() + "时"
            } else {
                val h = minute / 60
                val m = minute % 60
                h.toString() + "时" + m + "分"
            }
        }
        // 如果分钟大于1天
        if (minute >= 1440) {
            val d = minute / 60 / 24
            val h = minute / 60 % 24
            val m = minute % 60
            var s1: String? = null
            if (d > 0) {
                s1 = d.toString() + "天"
            }
            // h如果计算大于等于1再展示，否则只展示天和分钟
            if (h >= 1) {
                s1 += h.toString() + "时"
            }
            if (m > 0) {
                s1 += m.toString() + "分"
            }
            return s1
        }
        return null
    }

    fun getDayString(currentDate: Date): String {
        var string = ""
        string = if (TimeUtils.isToday(currentDate)) {
            "今日出发"
        } else {
            if (daysBetween(Date(), currentDate) == 1L) {
                "明日出发"
            } else {
                TimeUtils.getChineseWeek(currentDate)
            }
        }
        return string
    }


    fun turnTimeString(minute: Long): String {

        // 如果分钟大于1天
        if (minute >= 1440) {
            val d = minute / 60 / 24
            var s1: String? = null
            if (d > 0) {
                s1 = d.toString() + "天"
            }
            return s1!!
        }

        // 如果分钟小于24小时（1440分钟），返回小时和分钟
        if (minute in 60..1439) {
            return if (minute / 60 == 0L || minute / 60 >= 3L) {
                val h = minute / 60
                h.toString() + "时"
            } else {
                val h = minute / 60
                val m = minute % 60
                h.toString() + "时" + m + "分"
            }
        }

        // 如果分钟小于60，默认返回分钟
        if (minute in 1..59) {
            return minute.toString() + "分"
        }

        return ""
    }


    fun minuteToString(minute: Long): String {

        // 如果分钟小于60，默认返回分钟
        if (minute in 1..59) {
            return minute.toString() + "分"
        } else {
            val h = minute / 60
            val m = minute % 60
            return h.toString() + "时" + m + "分"
        }

    }

}