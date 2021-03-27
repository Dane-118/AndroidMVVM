package com.example.android.study.commonsdk.utils;

import com.blankj.utilcode.util.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static String formatTime(String date) {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = formater.parse(date);
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            result = format.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatTime2(String date) {
        // 输入格式2019-01-01，输出格式2019年01月01日
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = formater.parse(date);
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            result = format.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatTime3(String date) {
        // 输入格式2019-01-01 00:00:00，输出格式2019.01.01 00:00:00
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = formater.parse(date);
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            result = format.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatTime4(String date) {
        // 输入格式2019-01-01 00:00:00，输出格式2019.01.01 00:00:00
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date2 = formater.parse(date);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = format.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String formatTime5(String date) {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = formater.parse(date);
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
            result = format.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Date formatTimeToDate(String date) {
        // 输入格式2019-01-01，输出格式2019年01月01日
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = null;
        try {
            date2 = formater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date2;
    }

    public static Date formatTimeToDate2(String date) {
        // 输入格式2019-01-01，输出格式2019年01月01日
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
        Date date2 = null;
        try {
            date2 = formater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date2;
    }

    // a integer to xx:xx:xx
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 比较两个时间大小
     *
     * @param date1
     * @param date2
     * @return >0：date1>date2; 0:date1=date2; <0:date1<date2
     */
    public static int compareTime(String date1, String date2) {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        int result = 0;
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            Date d1 = formater.parse(date1);
            Date d2 = formater.parse(date2);
            c1.setTime(d1);
            c2.setTime(d1);
            result = c1.compareTo(c2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 格式化date
     *
     * @param date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatTimeToString(Date date) {
        // 输入格式2019-01-01 12:00:00，输出格式2019年01月01日
        String result = "";
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            result = formater.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取指定日后 后 dayAddNum 天的 日期
     *
     * @param day 日期，格式为String："2013-9-3";
     * @param dayAddNum 增加天数 格式为int;
     * @return
     */
    public static Date getDateFromStr(String day, int dayAddNum) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date newDate2 = new Date(nowDate.getTime() + dayAddNum * 24 * 60 * 60 * 1000);
        return newDate2;
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
    public static Date getYesterdayDate(Date nowDate, int dayAddNum) {
        Date newDate2 = new Date(nowDate.getTime() - dayAddNum * 24 * 60 * 60 * 1000);
        return newDate2;
    }

    /**
     * 获取指定日后 后 dayAddNum 天的 日期
     *
     * @param day 日期，格式为String："2013-9-3";
     * @return
     */
    public static long getDateFromStrByCalendar(String day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gc = new GregorianCalendar();
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
            gc.setTime(nowDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return gc.getTimeInMillis();
    }

    /**
     * yyyy年MM月dd日 HH时mm分ss秒
     */
    public static String formatDateTimeToStringChinese(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String newDate = null;
        try {
            newDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * yyyy年MM月dd日 HH时mm分
     */
    public static String formatDateTimeToStringChinese2(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        String newDate = null;
        try {
            newDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 格式化date "yyyy-MM-dd"
     */
    public static String formatDateToStringChinese(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        String newDate = null;
        try {
            newDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 格式化date "MM月dd日"
     */
    public static String formatDateToMonthDayStringChinese(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("MM月dd日");
        String newDate = null;
        try {
            newDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 格式化date "yyyy-MM-dd"
     */
    public static String formatDateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String newDate = null;
        try {
            newDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 格式化date "HH:mm"
     */
    public static String formatDateToTimeString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String newDate = null;
        try {
            newDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 格式化date "yyyy-MM-dd"
     */
    public static String formatDateToStringOnlyMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");
        String newDate = null;
        try {
            newDate = df.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * 获取年
     *
     * @return
     */
    public static int getYear() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.YEAR);
    }

    /**
     * 获取月
     *
     * @return
     */
    public static int getMonth() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取周
     *
     * @return
     */
    public static int getWeekOfYear() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取日
     *
     * @return
     */
    public static int getDay() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.DATE);
    }

    /**
     * 获取时
     *
     * @return
     */
    public static int getHour() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.HOUR);
    }

    /**
     * 获取时 24小时制
     *
     * @return
     */
    public static int getHourOfDay() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取分
     *
     * @return
     */
    public static int getMinute() {
        Calendar cd = Calendar.getInstance();
        return cd.get(Calendar.MINUTE);
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取年
     *
     * @return
     */
    public static int getYearByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取月
     *
     * @return
     */
    public static int getMonthByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日
     *
     * @return
     */
    public static int getDayByDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回昨天
     *
     * @param today
     * @return
     */
    public static Date yesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        return calendar.getTime();
    }

    /**
     * 返回明天
     *
     * @param today
     * @return
     */
    public static Date tomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }

    /**
     * 返回是不是昨天
     *
     * @param selectDate
     * @return
     */
    public static boolean isYesterday(Date selectDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(selectDate);

        if ((calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
            && (calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
            && (calendar.get(Calendar.DATE) == calendar2.get(Calendar.DATE))) {
            return true;
        }
        return false;
    }

    /**
     * 返回是不是今天
     *
     * @param selectDate
     * @return
     */
    public static boolean isToday(Date selectDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(selectDate);

        if ((calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
            && (calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))
            && (calendar.get(Calendar.DATE) == calendar2.get(Calendar.DATE))) {
            return true;
        }
        return false;
    }

    /**
     * 返回上个月
     *
     * @return
     */
    public static Date getLastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        return calendar.getTime();
    }

    /**
     * 返回是不是上个月
     *
     * @param selectDate
     * @return
     */
    public static boolean isLastMonth(Date selectDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(selectDate);

        if ((calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
            && (calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))) {
            return true;
        }
        return false;
    }

    /**
     * 返回是不是本月
     *
     * @param selectDate
     * @return
     */
    public static boolean isCurrentMonth(Date selectDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(selectDate);

        if ((calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
            && (calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))) {
            return true;
        }
        return false;
    }

    /**
     * 返回是不是下个月
     *
     * @param selectDate
     * @return
     */
    public static boolean isNextMonth(Date selectDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(selectDate);

        if ((calendar.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR))
            && (calendar.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH))) {
            return true;
        }
        return false;
    }

    public static String getWeekOfYear(String dateStr, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            Date date = format.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR) + "第" + calendar.get(Calendar.WEEK_OF_YEAR) + "周";
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getStartDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
            cal.get(Calendar.DAY_OF_MONTH);

    }

    /**
     * get the end day of given week no of a year.
     *
     * @param year
     * @param weekNo
     * @return
     */
    public static String getEndDayOfWeekNo(int year, int weekNo) {
        Calendar cal = getCalendarFormYear(year);
        cal.set(Calendar.WEEK_OF_YEAR, weekNo);
        cal.add(Calendar.DAY_OF_WEEK, 6);
        return cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +
            cal.get(Calendar.DAY_OF_MONTH);
    }

    private static Calendar getCalendarFormYear(int year) {
        Calendar cal = Calendar.getInstance();
        // 设置周一为一周的第一天
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.YEAR, year);
        return cal;
    }

    public static String getWeekOfYearString(Date date) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR) + "第" + calendar.get(Calendar.WEEK_OF_YEAR) + "周";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dateString
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(String dateString) {
        Date date = formatTimeToDate(dateString);
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * @param
     * @return true 大于
     * @description 年月日日否大于当前的年月日
     * @time 2020/4/27 14:34
     */
    public static boolean compareToCurrentData(String date) {
        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 创建日期转换对象：年 月 日
        Date today = new Date(); // 今天 实际日期是 2018-11-12 Debug：Wed Nov 12 12:08:12 CST 2018
        try {
            Date dateD = sdf.parse(date); // 将字符串转换为 date 类型 Debug：Sun Nov 11 00:00:00 CST 2018
            flag = dateD.getTime() >= today.getTime();
            // System.err.println("flag = "+flag); // flag = false
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return flag;
    }

    /**
     * @param
     * @return true 大于
     * @description 年月日日时分秒是否大于当前的年月日时分秒
     * @time 2020/4/27 14:34
     */
    public static boolean compareToCurrentDataSecond(String date) {
        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建日期转换对象：年 月 日
        Date today = new Date(); // 今天 实际日期是 2018-11-12 Debug：Wed Nov 12 12:08:12 CST 2018
        try {
            Date dateD = sdf.parse(date); // 将字符串转换为 date 类型 Debug：Sun Nov 11 00:00:00 CST 2018
            flag = dateD.getTime() >= today.getTime();
            // System.err.println("flag = "+flag); // flag = false
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return flag;
    }

    /**
     * @param
     * @return true 大于
     * @description 年月日日时分秒是否大于当前的年月日时分秒
     * @time 2020/4/27 14:34
     */
    public static boolean compareToDataSecond(String startDate, String endDate) {
        boolean flag = true;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 创建日期转换对象：年 月 日
        try {
            Date dateS = sdf.parse(startDate); // 将字符串转换为 date 类型 Debug：Sun Nov 11 00:00:00 CST 2018
            Date dateE = sdf.parse(endDate);
            flag = dateS.getTime() >= dateE.getTime();
            // System.err.println("flag = "+flag); // flag = false
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return flag;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smallDate 较小的时间
     * @param bigDate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static long daysBetween(Date smallDate, Date bigDate) {
        long day = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            smallDate = sdf.parse(sdf.format(smallDate));
            bigDate = sdf.parse(sdf.format(bigDate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smallDate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bigDate);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            day = Long.parseLong(String.valueOf(between_days));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smallDate 较小的时间
     * @param bigDate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int hoursBetween(Date smallDate, Date bigDate) {
        int hours = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            smallDate = sdf.parse(sdf.format(smallDate));
            bigDate = sdf.parse(sdf.format(bigDate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smallDate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bigDate);
            long time2 = cal.getTimeInMillis();
            long between_hours = (time2 - time1) / (1000 * 3600);
            hours = Integer.parseInt(String.valueOf(between_hours));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return hours;
    }



    /**
     * 计算两个日期之间相差的分钟
     *
     * @param smallDate 较小的时间
     * @param bigDate 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int minuteBetween(Date smallDate, Date bigDate) {
        int minute = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            smallDate = sdf.parse(sdf.format(smallDate));
            bigDate = sdf.parse(sdf.format(bigDate));
            Calendar cal = Calendar.getInstance();
            cal.setTime(smallDate);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bigDate);
            long time2 = cal.getTimeInMillis();
            long between_minute = (time2 - time1) / (1000 * 60);
            minute = Integer.parseInt(String.valueOf(between_minute));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return minute;
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
    public static String addDate(String day, int x) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");// 24小时制
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, x);// 24小时制
        // cal.add(Calendar.HOUR, x);12小时制
        date = cal.getTime();
        System.out.println("front:" + date);
        cal = null;
        return format.format(date);
    }

    /**
     * @param
     * @return
     * @description 加一天
     * @time 2020/4/17 17:06
     */
    public static String addDateByDay(String day, int x) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
        // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, x);// 24小时制
        // cal.add(Calendar.HOUR, x);12小时制
        date = cal.getTime();
        System.out.println("front:" + date);
        cal = null;
        return format.format(date);
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
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);
        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else {
            if ((date.get(Calendar.YEAR) == begin.get(Calendar.YEAR))
                && (date.get(Calendar.MONTH) == begin.get(Calendar.MONTH))
                && (date.get(Calendar.DATE) == begin.get(Calendar.DATE))) {
                return true;
            } else if ((date.get(Calendar.YEAR) == end.get(Calendar.YEAR))
                && (date.get(Calendar.MONTH) == end.get(Calendar.MONTH))
                && (date.get(Calendar.DATE) == end.get(Calendar.DATE))) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @return
     */
    public static boolean isInRangeDate(Date date, int startDay, int endDay) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, startDay - 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, endDay);

        return belongCalendar(date, calendar.getTime(), calendar2.getTime());
    }

    // 分钟转小时
    public static String turnDayHourMinuteString(Long minute) {
        // 如果传入的分钟是0，默认24小时
        if (0 == minute) {
            return 24 + "时";
        }
        // 如果分钟小于60，默认返回分钟
        if (0 < minute && minute < 60) {
            return minute + "分";
        }
        // 如果分钟小于24小时（1440分钟），返回小时和分钟
        if (60 <= minute && minute < 1440) {

            if (minute % 60 == 0) {
                Long h = minute / 60;
                return h + "时";
            } else {
                Long h = minute / 60;
                Long m = minute % 60;
                return h + "时" + m + "分";
            }

        }
        // 如果分钟大于1天
        if (minute >= 1440) {

            Long d = minute / 60 / 24;
            Long h = minute / 60 % 24;
            Long m = minute % 60;
            String s1 = null;
            if (d > 0) {
                s1 = d + "天";
            }
            // h如果计算大于等于1再展示，否则只展示天和分钟
            if (h >= 1) {
                s1 += h + "时";
            }
            if (m > 0) {
                s1 += m + "分";
            }

            return s1;
        }
        return null;
    }

    public static final String getDayString(Date currentDate) {
        String string = "";
        if (TimeUtils.isToday(currentDate)) {

            string = "今日出发";
        } else {

            if (DateUtil.daysBetween(new Date(), currentDate) == 1L) {
                string = "明日出发";
            } else {
                string = TimeUtils.getChineseWeek(currentDate);
            }
        }
        return string;
    }

}
