package com.example.android.study.commonres.widget.dialog.date

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.*
import com.example.android.study.commonres.R
import com.example.android.study.commonres.utils.DateUtil
import com.example.android.study.commonres.utils.onClick
import com.lxj.xpopup.core.BottomPopupView
import com.lxj.xpopup.util.XPopupUtils
import com.sunyuan.calendarlibrary.CalendarAdapter
import com.sunyuan.calendarlibrary.CalendarView
import com.sunyuan.calendarlibrary.CalendarViewWrapper
import com.sunyuan.calendarlibrary.SelectionMode
import com.sunyuan.calendarlibrary.model.CalendarDay
import com.sunyuan.calendarlibrary.model.CalendarSelectDay
import com.sunyuan.calendarlibrary.model.DateVO
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @ClassName: MultipleDateSelectBottomSheetView
 * @Description: 日期多选弹窗
 * @Author: Jiao Peng
 */
@SuppressLint("ViewConstructor")
class MultipleDateSelectBottomSheetView(
        context: Context,
        private var rangeDay: Int? = null,
        private var rangeMonth: Int? = null,
        private var reservationDateStart: Int = 30,
        private var reservationDateEnd: Int = 60,
        private var tipsVisible: Boolean = false,
        private var selectDates: ArrayList<CalendarDay>? = null,
        private var onOkButtonClickListener: ((ArrayList<CalendarDay>) -> Unit)? = null,
) : BottomPopupView(context), AdapterView.OnItemClickListener {

    private var itemList: MutableList<String> = ArrayList()
    private var mOnItemClickListener: OnItemClickListener? = null
    private var iv_close: ImageView? = null
    private var calendarView: CalendarView? = null
    private var calendarSelectDay: CalendarSelectDay<CalendarDay>? = null
    private var btn_add: Button? = null
    private var tv_train_tips: TextView? = null
    private var currentSelectDateStr: String? = null
    private val dateVO = DateVO()
    private var calendarBuilder: CalendarViewWrapper.CalendarBuilder? = null
    private var list = arrayListOf<CalendarDay>()

    override fun getImplLayoutId(): Int {
        return R.layout.public_bottom_sheet_single_date_select
    }

    override fun onCreate() {
        super.onCreate()
        initView()
    }

    override fun getMaxHeight(): Int {
        return (XPopupUtils.getScreenHeight(context) * 0.85f).toInt()
    }

    private fun initView() {
        calendarView = findViewById(R.id.calendar_view)
        iv_close = findViewById(R.id.iv_close)
        tv_train_tips = findViewById(R.id.tv_train_tips)
        btn_add = findViewById(R.id.btn_add)
        btn_add?.visibility = View.VISIBLE
        initSelectCalendar(Date())
        initCalendarView()
        rangeMonth?.let {
            setDateRange(it)
        }
        rangeDay?.let {
            setDateRangeDay(it)

        }
        setReservationDateList(reservationDateStart, reservationDateEnd)
        setTrainTipsVisible(tipsVisible)

        val adapter = Objects.requireNonNull(calendarView!!.adapter) as CalendarAdapter
        if (selectDates != null && selectDates!!.size > 0) {
            adapter.calendarSelectDay.multiSelectDay.clear()
            adapter.calendarSelectDay.multiSelectDay.addAll(selectDates!!)
            adapter?.refresh()
            calendarView?.refresh()
        }

        list.clear()
        selectDates?.let { list.addAll(it) }

//        setSelectDate(selectDate)

        iv_close?.onClick {
            dismiss()
        }
        btn_add?.onClick {
            if (list.size != 0) {
                onOkButtonClickListener?.invoke(list)
                dismiss()
            } else {
                Toast.makeText(context, R.string.dialog_choose_one_date, Toast.LENGTH_SHORT).show()
            }
        }
        tv_train_tips?.text = "抢票高峰期，建议选择多个日期同时抢票，成功率倍增"
    }

    @SuppressLint("SimpleDateFormat")
    private fun getMinDate(currentDate: Int): String {
        val now = Calendar.getInstance()
        now.add(Calendar.DAY_OF_MONTH, currentDate)
        val endDate = SimpleDateFormat("MM月dd日").format(now.time)
        return endDate
    }

    /**
     * 默认选择当前日期
     */
    private fun initSelectCalendar(date: Date) {
        if (calendarSelectDay == null) {
            calendarSelectDay = CalendarSelectDay()
        }
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendarSelectDay!!.firstSelectDay = CalendarDay(calendar[Calendar.YEAR], calendar[Calendar.MONTH],
                calendar[Calendar.DATE])
        currentSelectDateStr = DateUtil.formatDateToMonthDayStringChinese(calendarSelectDay!!.firstSelectDay.toDate())
        dateVO.startDate = calendarSelectDay!!.firstSelectDay.toDate()
        dateVO.startDateString = DateUtil.formatDateToMonthDayStringChinese(calendarSelectDay!!.firstSelectDay.toDate())
        dateVO.startDateFormat = DateUtil.formatDateToString(calendarSelectDay!!.firstSelectDay.toDate())
    }

    private fun initCalendarView() {
        val calendar = Calendar.getInstance()
        val minDate = calendar.time
        calendar.add(Calendar.MONTH, 12)
        val maxDate = calendar.time
        calendarBuilder = CalendarViewWrapper.wrap(calendarView) // 设置最大最小日期范围 展示三个月数据
                .setDateRange(minDate, maxDate) // 设置默认选中日期
                .setCalendarSelectDay(calendarSelectDay)
                // 设置选择模式为范围选择
                .setSelectionMode(SelectionMode.MULTI)
                // 设置选中回调
                .setOnCalendarSelectDayListener { calendarSelectDay: CalendarSelectDay<CalendarDay?> ->
                    list.clear()
                    list.addAll(calendarSelectDay.multiSelectDay as ArrayList<CalendarDay>)
                    if (list.size > 5) {
                        calendarSelectDay.multiSelectDay.removeAt(0)
                        list.removeAt(0)
                        calendarView?.refresh()
                    }
                } // 头部月份是否悬停
                .setStick(true) // 是否展示头部月份
                .setShowMonthTitleView(true) // 设置展示头部月份的回调用于创建头部月份View
                .setMonthTitleViewCallBack { position: Int, date: Date? ->
                    val view = View.inflate(context, R.layout.calendar_month_title, null)
                    val tvMonthTitle = view.findViewById<TextView>(R.id.tv_month_title)
                    tvMonthTitle.text = DateUtil.formatDateToStringOnlyMonth(date)
                    view
                }
        calendarBuilder?.display()

        // 根据指定日期得到position位置
        val position = calendarView!!.covertToPosition(calendarSelectDay!!.firstSelectDay)
        if (position != -1) {
            // 滚动到指定位置
            calendarView!!.smoothScrollToPosition(position)
        }
    }

    fun setOnItemClickListener(myOnItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = myOnItemClickListener
    }

    fun setTrainTipsVisible(isVisible: Boolean) {
        this.tipsVisible = isVisible
        tv_train_tips?.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    /**
     * @param month 在当前的基础上增加几个可选择的月份
     * @return
     * @description 设置时间范围
     * @time 2020/5/14 10:21
     */
    fun setDateRange(month: Int) {
        this.rangeMonth = month
        val calendar = Calendar.getInstance()
        val minDate = calendar.time
        calendar.add(Calendar.MONTH, month)
        val maxDate = calendar.time
        if (calendarBuilder != null) {
            calendarBuilder!!.setDateRange(minDate, maxDate)
            calendarBuilder!!.notifyView()
        }
    }

    /**
     * @param month 在当前的基础上增加几个可选择的月份(自然月)
     * @return
     * @description 设置时间范围
     * @time 2020/5/14 10:21
     */
    fun setDateRangeAuto(month: Int) {
        val calendar = Calendar.getInstance()
        val minDate = calendar.time
        calendar.add(Calendar.MONTH, month)
        calendar[Calendar.DAY_OF_MONTH] = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        val maxDate = calendar.time
        if (calendarBuilder != null) {
            calendarBuilder!!.setDateRange(minDate, maxDate)
            calendarBuilder!!.notifyView()
        }
    }

    /**
     * @param days 可选多少天
     * @return
     * @description 设置时间范围
     * @time 2020/5/14 10:21
     */
    fun setDateRangeDay(days: Int) {
        this.rangeDay = days
        val calendar = Calendar.getInstance()
        val minDate = calendar.time
        calendar.add(Calendar.DAY_OF_YEAR, days - 1)
        val maxDate = calendar.time
        if (calendarBuilder != null) {
            calendarBuilder!!.setDateRange(minDate, maxDate)
            calendarBuilder!!.notifyView()
        }
    }

    /**
     * @param
     * @return
     * @description 设置当前选中的日期
     * @time 2020/4/28 19:21
     */
    // TODO 待研究
    fun setSelectDate(selectDate: String?) {
        val date = DateUtil.formatTimeToDate(selectDate)
        if (date != null) {
            initSelectCalendar(date)
            if (calendarBuilder != null) {
                calendarBuilder!!.setCalendarSelectDay(calendarSelectDay)
                calendarBuilder!!.notifyView()
                // 根据指定日期得到position位置
                val position = calendarView!!.covertToPosition(calendarSelectDay!!.firstSelectDay)
                if (position != -1) {
                    // 滚动到指定位置
                    calendarView!!.smoothScrollToPosition(position)
                }
            }
        }
    }

    /**
     * @param
     * @return
     * @description 设置当前选中的日期
     * @time 2020/4/28 19:21
     */
    fun setSelectDate(selectDate: Date) {
//        this.selectDate = selectDate
        initSelectCalendar(selectDate)
        if (calendarBuilder != null) {
            calendarBuilder!!.setCalendarSelectDay(calendarSelectDay)
            calendarBuilder!!.notifyView()
            // 根据指定日期得到position位置
            val position = calendarView!!.covertToPosition(calendarSelectDay!!.firstSelectDay)
            if (position != -1) {
                // 滚动到指定位置
                calendarView!!.smoothScrollToPosition(position)
            }
        }
    }

    /**
     * @param startDay 从距离当前时间的第几天开始
     * endDay 到距离当前时间的第几天结束
     * @return
     * @description 设置可预定的日期，连续的日期
     * @time 2020/5/20 13:40
     */
    open fun setReservationDateList(startDay: Int, endDay: Int) {
        if (startDay <= 0 || endDay <= 0 || startDay > endDay) {
            return
        }
        this.reservationDateStart = startDay
        this.reservationDateEnd = endDay

        val dateList: MutableList<Date> = ArrayList()
        val calendar = Calendar.getInstance()
        // 往前
        calendar.add(Calendar.DAY_OF_YEAR, startDay - 2)
        for (i in startDay..endDay) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
            val date = calendar.time
            dateList.add(date)
        }
        setReservationDateList(dateList)
    }

    /**
     * @param
     * @return
     * @description 设置可预定的日期的集合 ，可以不连续
     * @time 2020/5/20 13:49
     */
    fun setReservationDateList(dateList: List<Date>?) {
        if (dateList == null || dateList.isEmpty()) {
            return
        }
        val calendarDayList: MutableList<CalendarDay> = ArrayList()
        for (date in dateList) {
            val calendar = Calendar.getInstance()
            calendar.time = date
            val calendarDay = CalendarDay(calendar[Calendar.YEAR], calendar[Calendar.MONTH],
                    calendar[Calendar.DATE])
            calendarDayList.add(calendarDay)
        }
        calendarSelectDay?.reservationDay = calendarDayList
        if (calendarBuilder != null) {
            calendarBuilder!!.setCalendarSelectDay(calendarSelectDay)
            calendarBuilder!!.notifyView()
        }
    }

    fun setPopupItemList(items: MutableList<String>?) {
        if (items == null) {
            return
        }
        itemList = items
    }

    fun notifyData(items: List<String>) {
        if (items.isNullOrEmpty()) {
            return
        }
        itemList.clear()
        itemList.addAll(items)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
//        dismiss()
//        if (mOnItemClickListener != null) {
//            mOnItemClickListener!!.onItemClick(position)
//        }
    }

    /**
     * 点击事件
     */
    interface OnItemClickListener {
        fun onItemClick(index: Int)
    }

}