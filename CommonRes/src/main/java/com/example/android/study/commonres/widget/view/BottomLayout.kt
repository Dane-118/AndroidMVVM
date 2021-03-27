package com.example.android.study.commonres.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.example.android.study.commonres.R
import com.example.android.study.commonres.ext.text
import com.example.android.study.commonres.utils.DoubleClickUtils
import me.hgj.jetpackmvvm.ext.view.gone

/**
 * 描述：
 *
 * @author {Wang Peng} by 1/7/21
 */
class BottomLayout : LinearLayout, View.OnClickListener {
    lateinit var view: View
    lateinit var cl_start: CustomLayout
    lateinit var cl_time: CustomLayout
    lateinit var cl_price: CustomLayout
    lateinit var cl_filter: TextView
    var isStart = false
    var isTime = true
    var isPrice = true
    var isFilter = false
    var mIFilterCallBack: IFilterCallBack? = null

    var count: Int? = null
    var lastViewStr = ""

    constructor(context: Context?) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        view = LayoutInflater.from(context).inflate(R.layout.layout_bottom_filter, this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomLayout)
        count = typedArray.getString(R.styleable.BottomLayout_count)?.toInt()
        lastViewStr = typedArray.getString(R.styleable.BottomLayout_last_view_str) ?: "筛选"
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    private fun initView() {
        cl_start = view.findViewById(R.id.cl_start)
        cl_time = view.findViewById(R.id.cl_time)
        cl_price = view.findViewById(R.id.cl_price)
        cl_filter = view.findViewById(R.id.cl_filter)
        cl_start.setOnClickListener(this)
        cl_time.setOnClickListener(this)
        cl_price.setOnClickListener(this)
        cl_filter.setOnClickListener(this)
        cl_filter.text(lastViewStr)
        if (count == 3) {
            cl_price.gone()
        } else if (count == 2) {
            cl_price.gone()
            cl_time.gone()
        }
    }

    fun setFirstClick() {
        cl_start.performClick()
    }

    override fun onClick(view: View) {
        if (DoubleClickUtils.isValid(view)) {
            when (view.id) {
                R.id.cl_start -> {
                    if (isStart) {
                        cl_start.setUp()
                    } else {
                        cl_start.setDown()
                    }
                    mIFilterCallBack?.onStartTimeSort(isStart)

                    cl_time.recover()
                    cl_price.recover()
                    if("筛选" == lastViewStr){
                        cl_filter.setTextColor(ColorUtils.getColor(R.color.color_text_un_press))
                    }
                    isTime = true
                    isPrice = true
                    isStart = !isStart

                }
                R.id.cl_time -> {
                    if (isTime) {
                        cl_time.setUp()
                    } else {
                        cl_time.setDown()
                    }
                    mIFilterCallBack?.onUseTimeSort(isTime)

                    isTime = !isTime
                    cl_start.recover()
                    cl_price.recover()
                    if("筛选" == lastViewStr){
                        cl_filter.setTextColor(ColorUtils.getColor(R.color.color_text_un_press))
                    }
                    isStart = true
                    isPrice = true
                }
                R.id.cl_price -> {
                    if (isPrice) {
                        cl_price.setUp()
                    } else {
                        cl_price.setDown()
                    }
                    mIFilterCallBack?.onPriceSort(isPrice)

                    isPrice = !isPrice
                    cl_start.recover()
                    if("筛选" == lastViewStr){
                        cl_filter.setTextColor(ColorUtils.getColor(R.color.color_text_un_press))
                    }
                    cl_time.recover()
                    isStart = true
                    isTime = true
                }
                R.id.cl_filter -> {
                    mIFilterCallBack?.onFilter()
                }

            }
        }
    }

}