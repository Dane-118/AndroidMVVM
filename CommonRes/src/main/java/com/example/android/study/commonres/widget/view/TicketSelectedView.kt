package com.example.android.study.commonres.widget.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.StringUtils.getString
import com.blankj.utilcode.util.TimeUtils
import com.example.android.study.commonres.R
import com.example.android.study.commonres.ext.YYYY_MM_DD
import com.example.android.study.commonres.ext.text
import com.example.android.study.commonres.utils.DateUtil
import java.util.*

/**
 * 描述：
 *
 * @author {Wang Peng} by 3/16/21
 */
class TicketSelectedView : ConstraintLayout {
    lateinit var view: View
    lateinit var tv_ahead_day: TextView
    lateinit var tv_current_date: CheckBox
    lateinit var tv_after_day: TextView

    constructor(context: Context) : super(context){
        initView()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        view = LayoutInflater.from(context).inflate(R.layout.ticket_include_item_date, this)
        initView()
    }

    private fun initView() {
        tv_ahead_day = view.findViewById(R.id.tv_ahead_day)
        tv_current_date = view.findViewById(R.id.tv_current_date)
        tv_after_day = view.findViewById(R.id.tv_after_day)
    }


    fun setViewStatus(selectedDate: String, maxDate: Long) {
        when (DateUtil.daysBetween(Date(), TimeUtils.string2Date(selectedDate, YYYY_MM_DD))) {
            0L -> {
                tv_current_date.text = String.format(getString(R.string.today), selectedDate)
                tv_ahead_day.setTextColor(Color.parseColor("#9AFFFFFF"))
                tv_ahead_day.setCompoundDrawablesWithIntrinsicBounds(ResourceUtils.getDrawable(R.drawable.ic_train_list_arrow_left_gray), null, null, null)
                tv_after_day.isEnabled = true
                tv_after_day.setTextColor(ColorUtils.getColor(R.color.white))
                tv_ahead_day.isEnabled = false
            }
            1L -> {
                tv_current_date.text = String.format(getString(R.string.tomorrow), selectedDate)
                tv_ahead_day.isEnabled = true
                tv_ahead_day.setTextColor(ColorUtils.getColor(R.color.white))
                tv_ahead_day.setCompoundDrawablesWithIntrinsicBounds(ResourceUtils.getDrawable(R.drawable.ic_train_list_arrow_left_white), null, null, null)
                tv_after_day.isEnabled = true
                tv_after_day.setTextColor(ColorUtils.getColor(R.color.white))
            }
            2L -> {
                tv_current_date.text = String.format(getString(R.string.after_tom), selectedDate)
                tv_ahead_day.isEnabled = true
                tv_ahead_day.setTextColor(ColorUtils.getColor(R.color.white))
                tv_ahead_day.setCompoundDrawablesWithIntrinsicBounds(ResourceUtils.getDrawable(R.drawable.ic_train_list_arrow_left_white), null, null, null)
                tv_after_day.isEnabled = true
                tv_after_day.setTextColor(ColorUtils.getColor(R.color.white))
            }
            maxDate -> {
                tv_after_day.isEnabled = false
                tv_ahead_day.isEnabled = true
                tv_ahead_day.setTextColor(ColorUtils.getColor(R.color.white))
                tv_ahead_day.setCompoundDrawablesWithIntrinsicBounds(ResourceUtils.getDrawable(R.drawable.ic_train_list_arrow_left_white), null, null, null)
                tv_after_day.setTextColor(Color.parseColor("#9AFFFFFF"))
                tv_after_day.setCompoundDrawablesWithIntrinsicBounds(null, null, ResourceUtils.getDrawable(R.drawable.ic_train_list_arrow_right_gray), null)
                tv_current_date.text("$selectedDate ${TimeUtils.getChineseWeek(TimeUtils.string2Date(selectedDate, YYYY_MM_DD))}")
            }
            else -> {
                tv_after_day.isEnabled = true
                tv_ahead_day.isEnabled = true
                tv_after_day.setTextColor(ColorUtils.getColor(R.color.white))
                tv_ahead_day.setTextColor(ColorUtils.getColor(R.color.white))
                tv_ahead_day.setCompoundDrawablesWithIntrinsicBounds(ResourceUtils.getDrawable(R.drawable.ic_train_list_arrow_left_white), null, null, null)
                tv_after_day.setCompoundDrawablesWithIntrinsicBounds(null, null, ResourceUtils.getDrawable(R.drawable.ic_train_list_arrow_right_white), null)
                tv_current_date.text("$selectedDate ${TimeUtils.getChineseWeek(TimeUtils.string2Date(selectedDate, YYYY_MM_DD))}")

            }
        }
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}
}