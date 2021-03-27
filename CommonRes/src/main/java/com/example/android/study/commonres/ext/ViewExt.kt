package com.example.android.study.commonres.ext

import android.content.Context
import android.text.Html
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.CompoundButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SizeUtils
import com.example.android.study.commonres.R
import com.fondesa.recyclerviewdivider.dividerBuilder
import com.google.android.flexbox.*

/**
 * 描述：
 *
 * @author {Wang Peng} by 12/27/20
 */
fun TextView?.mText(): String {
    return this?.text.toString().trim()
}


fun TextView?.text(string: CharSequence?) {
    this?.let {
        this.text = string ?: ""
    }
}

fun TextView?.text4Html(string: CharSequence?) {
    this?.let {
        if (!TextUtils.isEmpty(string)) {
            this.text = Html.fromHtml(string.toString())
        }
    }
}


fun CompoundButton?.isChecked(boolean: Boolean) {
    this?.isChecked = boolean
}

fun View?.enabled(boolean: Boolean) {
    this?.isEnabled = boolean
}

fun View?.scanAnimation() {
    val mTop2Bottom = TranslateAnimation(
            TranslateAnimation.ABSOLUTE, 0f,
            TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0f,
            TranslateAnimation.RELATIVE_TO_PARENT, 0.5f
    )
    mTop2Bottom.repeatMode = Animation.RESTART
    mTop2Bottom.interpolator = LinearInterpolator()
    mTop2Bottom.duration = 2500
    mTop2Bottom.repeatCount = Animation.INFINITE
    mTop2Bottom.isFillEnabled = true
    mTop2Bottom.fillAfter = true
    this?.startAnimation(mTop2Bottom)
}


/**
 * 分割线
 */
fun RecyclerView?.dividerLine(
    color: Int = R.color.white,
    size: Int = 2,
    left: Float = 0f,
    right: Float = 0f,
    showLastDivider: Boolean = false,
    showFirstDivider: Boolean = false,
    typedValue: Int = TypedValue.COMPLEX_UNIT_PX
) {
    this?.let {
        val dividerBuilder = it.context.dividerBuilder()
//        dividerBuilder.color(color)
        dividerBuilder.colorRes(color)
        dividerBuilder.size(size, typedValue)
        dividerBuilder.insets(SizeUtils.dp2px(left), SizeUtils.dp2px(right))
        if (showFirstDivider)
            dividerBuilder.showFirstDivider()
        if (showLastDivider)
            dividerBuilder.showLastDivider()
        dividerBuilder.build().addTo(it)
    }

}

fun RecyclerView.setFlexBoxLayoutManager(context: Context) {
    val manager = FlexboxLayoutManager(context)
    manager.flexWrap = FlexWrap.WRAP
    manager.flexDirection = FlexDirection.ROW
    manager.alignItems = AlignItems.STRETCH
    manager.justifyContent = JustifyContent.FLEX_START
    this.layoutManager = manager
}

/**
 * 设置Button的右侧文字
 */
fun TextView.setEndText(text: String, color: Int, padding: Int = SizeUtils.dp2px(20F)) {
    this.minHeight = 0
    this.minWidth = 0
    this.minimumHeight = 0
    this.minimumWidth = 0
    this.text = text
    this.setTextColor(color)
    this.setCompoundDrawables(null, null, null, null)
    this.setPadding(0, 0, padding, 0)
}



