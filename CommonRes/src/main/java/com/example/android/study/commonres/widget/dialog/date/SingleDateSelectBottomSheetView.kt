package com.example.android.study.commonres.widget.dialog.date

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.*
import com.blankj.utilcode.util.ScreenUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.android.study.commonres.R
import com.example.android.study.commonres.ext.text
import com.example.android.study.commonres.utils.DateUtil
import com.example.android.study.commonres.utils.onClick
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sunyuan.calendarlibrary.CalendarView
import com.sunyuan.calendarlibrary.CalendarViewWrapper
import com.sunyuan.calendarlibrary.SelectionMode
import com.sunyuan.calendarlibrary.model.CalendarDay
import com.sunyuan.calendarlibrary.model.CalendarSelectDay
import com.sunyuan.calendarlibrary.model.DateVO
import me.hgj.jetpackmvvm.ext.view.visibleOrGone
import java.text.SimpleDateFormat
import java.util.*

/**
 * @ClassName: SingleDateSelectBottomSheetView
 * @Description: 描述
 * @Author: Shao Yang
 */
class SingleDateSelectBottomSheetView(context: Context) : BottomSheetDialog(context), AdapterView.OnItemClickListener {
    private val mBottomSheetCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View,
                                    @BottomSheetBehavior.State newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
                BottomSheetBehavior.from(bottomSheet).setState(
                        BottomSheetBehavior.STATE_COLLAPSED)
            } else if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }
    private var mContext: Context? = null
    private var inflater: LayoutInflater? = null
    private var rootView: View? = null
    private val mPeekHeight: Int
    private val mMaxHeight: Int
    private var mWindow: Window? = null
    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null
    private var itemList: MutableList<String> = ArrayList()
    private var mOnItemClickListener: OnItemClickListener? = null
    private var iv_close: ImageView? = null
    private var calendarView: CalendarView? = null
    private var calendarSelectDay: CalendarSelectDay<CalendarDay>? = null
    private var btn_add: Button? = null
    private var tv_train_tips: TextView? = null
    private var currentSelectDateStr: String? = null
    private var tips: String? = null
    var onOkButtonClickListener: ((DateVO) -> Unit)? = null
    private val dateVO = DateVO()
    private var calendarBuilder: CalendarViewWrapper.CalendarBuilder? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setPeekHeight()
        setMaxHeight()
        setBottomSheetCallback()
    }

    private fun setPeekHeight() {
        if (mPeekHeight <= 0) {
            return
        }
        if (bottomSheetBehavior != null) {
            mBottomSheetBehavior!!.peekHeight = mPeekHeight
        }
    }

    private fun setMaxHeight() {
        if (mMaxHeight <= 0) {
            return
        }
        mWindow?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, mMaxHeight)
        mWindow?.setGravity(Gravity.BOTTOM)
    }

    private val bottomSheetBehavior: BottomSheetBehavior<*>?
        private get() {
            if (mBottomSheetBehavior != null) {
                return mBottomSheetBehavior
            }
            val view = mWindow!!.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                    ?: return null
            mBottomSheetBehavior = BottomSheetBehavior.from(view)
            mBottomSheetBehavior?.isHideable = false
            return mBottomSheetBehavior
        }

    private fun setBottomSheetCallback() {
        if (bottomSheetBehavior != null) {
            mBottomSheetBehavior!!.setBottomSheetCallback(mBottomSheetCallback)
        }
    }

    private fun initView(context: Context) {
        mContext = context
        inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        rootView = inflater?.inflate(R.layout.public_bottom_sheet_single_date_select, null)
        calendarView = rootView?.findViewById(R.id.calendar_view)
        iv_close = rootView?.findViewById(R.id.iv_close)
        tv_train_tips = rootView?.findViewById(R.id.tv_train_tips)
        btn_add = rootView?.findViewById(R.id.btn_add)



        initSelectCalendar(Date())
        initCalendarView()
        setDateRangeDay(60)
        setReservationDateList(30, 60)
        iv_close?.onClick { dismiss() }
        btn_add?.onClick {
            if (TextUtils.isEmpty(currentSelectDateStr)) {
                ToastUtils.showShort(context.getString(R.string.date_not_empty))
                return@onClick
            }
            if (TextUtils.isEmpty(dateVO.startDateString)) {
                ToastUtils.showShort(context.getString(R.string.date_not_empty))
                return@onClick
            }
            onOkButtonClickListener?.invoke(dateVO)
            dismiss()
        }
        this.setContentView(rootView!!)
        (rootView?.parent as View).setBackgroundColor(mContext!!.resources.getColor(android.R.color.transparent))


//        tv_train_tips?.text = String.format("%s的车票已开售，您也可以预约%s之前的车票，开售立即抢票", getMinDate(29), getMinDate(60))
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
                .setSelectionMode(SelectionMode.SINGLE)
                // 设置选中回调
                .setOnCalendarSelectDayListener { calendarSelectDay: CalendarSelectDay<CalendarDay?> ->
                    val firstSelectDay = calendarSelectDay.firstSelectDay
                    if (firstSelectDay != null) {
                        currentSelectDateStr = DateUtil.formatDateToMonthDayStringChinese(firstSelectDay.toDate())
                        dateVO.startDate = firstSelectDay.toDate()
                        dateVO.startDateString = DateUtil.formatDateToMonthDayStringChinese(firstSelectDay.toDate())
                        dateVO.startDateFormat = DateUtil.formatDateToString(firstSelectDay.toDate())
                        if (TextUtils.isEmpty(currentSelectDateStr) || TextUtils.isEmpty(dateVO.startDateString)) {
                            ToastUtils.showShort(context.getString(R.string.date_not_empty))
                            return@setOnCalendarSelectDayListener
                        }
                        if (onOkButtonClickListener != null) {
                            onOkButtonClickListener!!.invoke(dateVO)
                            dismiss()
                        }
                    }
                } // 头部月份是否悬停
                .setStick(true) // 是否展示头部月份
                .setShowMonthTitleView(true) // 设置展示头部月份的回调用于创建头部月份View
                .setMonthTitleViewCallBack { position: Int, date: Date? ->
                    val view = View.inflate(mContext, R.layout.calendar_month_title, null)
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

    fun setTrainTipsVisible(isVisible: String?) {
        this.tips = isVisible
        tv_train_tips?.visibleOrGone(!TextUtils.isEmpty(tips))
        tv_train_tips?.text(isVisible)
    }

    /**
     * @param month 在当前的基础上增加几个可选择的月份
     * @return
     * @description 设置时间范围
     * @time 2020/5/14 10:21
     */
    fun setDateRange(month: Int) {
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
        val calendar = Calendar.getInstance()
        val minDate = calendar.time
        calendar.add(Calendar.DAY_OF_YEAR, days)
        val maxDate = calendar.time
        calendarBuilder?.let {
            it.setDateRange(minDate, maxDate)
            it.notifyView()
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
    fun setSelectDate(selectDate: Date?): SingleDateSelectBottomSheetView {
        initSelectCalendar(selectDate ?: Date())
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
        return this
    }

    /**
     * @param startDay 从距离当前时间的第几天开始
     * endDay 到距离当前时间的第几天结束
     * @return
     * @description 设置可预定的日期，连续的日期
     * @time 2020/5/20 13:40
     */
    fun setReservationDateList(startDay: Int, endDay: Int) {
        if (startDay <= 0 || endDay <= 0 || startDay > endDay) {
            return
        }
        val dateList: MutableList<Date> = ArrayList()
        val calendar = Calendar.getInstance()
        // 往前
        calendar.add(Calendar.DAY_OF_YEAR, startDay)
        for (i in startDay until endDay) {
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
        calendarSelectDay!!.reservationDay = calendarDayList
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
        dismiss()
        if (mOnItemClickListener != null) {
            mOnItemClickListener!!.onItemClick(position)
        }
    }

    /**
     * 点击事件
     */
    interface OnItemClickListener {
        fun onItemClick(index: Int)
    }

    /**
     * 确认点击
     */
    interface OnOkButtonClickListener {
        fun onOkClick(dateVO: DateVO)
    }

    companion object {
        private const val TAG = "SingleDateSelectBottomS"
    }

    init {
        mWindow = window
        mPeekHeight = (ScreenUtils.getScreenHeight() * 0.8).toInt()
        mMaxHeight = mPeekHeight
        initView(context)
    }


}