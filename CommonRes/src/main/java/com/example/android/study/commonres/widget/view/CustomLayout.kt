package com.example.android.study.commonres.widget.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ColorUtils.getColor
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.ResourceUtils.getDrawable
import com.example.android.study.commonres.R
import kotlinx.android.synthetic.main.layout_train_list.view.*

/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/6/30
 */
class CustomLayout : LinearLayout {
    lateinit var name: String
    var enable: Boolean = false


    constructor(context: Context?) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_train_list, this)
        // 获取属性集合 TypedArray
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomLayout)
        name = typedArray.getString(R.styleable.CustomLayout_text_name).toString()
        enable = typedArray.getString(R.styleable.CustomLayout_is_enable).toBoolean()
        initView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    private fun initView() {
        tv_start?.text = name
        if (enable) {
            setUp()
        }
    }


    fun setStatus(up: Boolean,
                  @ColorInt color: Int = ColorUtils.getColor(R.color.train_text_un_press),
                  upDrawable: Drawable = ResourceUtils.getDrawable(R.drawable.ic_up_press_un),
                  downDrawable: Drawable = ResourceUtils.getDrawable(R.drawable.ic_down_un_press)) {
        iv_up?.setImageDrawable(upDrawable)
        iv_down?.setImageDrawable(downDrawable)
        tv_start?.setTextColor(color)
    }


    fun setUp() {
        iv_up?.setImageDrawable(getDrawable(R.drawable.ic_up_press))
        iv_down?.setImageDrawable(getDrawable(R.drawable.ic_down_un_press))
        tv_start?.setTextColor(getColor(R.color.color_text_press))
    }

    fun setDown() {
        iv_up?.setImageDrawable(getDrawable(R.drawable.ic_up_press_un))
        iv_down?.setImageDrawable(getDrawable(R.drawable.ic_down_press))
        tv_start?.setTextColor(getColor(R.color.color_text_press))
    }

    fun recover() {
        tv_start?.setTextColor(getColor(R.color.train_text_un_press))
        iv_up?.setImageDrawable(getDrawable(R.drawable.ic_up_press_un))
        iv_down?.setImageDrawable(getDrawable(R.drawable.ic_down_un_press))
    }
}