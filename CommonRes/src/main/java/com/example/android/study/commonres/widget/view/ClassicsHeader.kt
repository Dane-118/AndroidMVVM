package com.example.android.study.commonres.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.example.android.study.commonres.R
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle


class ClassicsHeader : LinearLayout, RefreshHeader {
    private lateinit var mHeaderText: TextView //标题文本
    lateinit var imageView: ImageView

    constructor(context: Context) : super(context) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(context)
    }

    private fun initView(context: Context) {
        val inflate = View.inflate(context, R.layout.srl_classics_header, this)
        mHeaderText = inflate.findViewById(R.id.tv_refresh)
    }

    override fun getView(): View {
        return this //真实的视图就是自己，不能返回null
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate //指定为平移，不能null
    }

    override fun onStartAnimator(layout: RefreshLayout, headHeight: Int, maxDragHeight: Int) {

    }

    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
//        animationDrawable!!.stop() //停止动画
        if (success) {
            mHeaderText.text = "刷新完成"
        } else {
            mHeaderText.text = "刷新失败"
        }
        return 200 //延迟500毫秒之后再弹回
    }

    override fun onStateChanged(refreshLayout: RefreshLayout, oldState: RefreshState, newState: RefreshState) {
        when (newState) {
            RefreshState.None, RefreshState.PullDownToRefresh -> {
                mHeaderText.text = "下拉开始刷新"
            }
            RefreshState.Refreshing -> {
                mHeaderText.text = "正在刷新"
            }
            RefreshState.ReleaseToRefresh -> mHeaderText.text = "释放立即刷新"
        }
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {}
    override fun onMoving(isDragging: Boolean, percent: Float, offset: Int, height: Int, maxDragHeight: Int) {}
    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {}
    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {}
    override fun setPrimaryColors(@ColorInt vararg colors: Int) {}
}