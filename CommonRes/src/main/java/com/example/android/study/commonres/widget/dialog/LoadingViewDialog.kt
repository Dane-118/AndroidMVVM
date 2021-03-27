package com.example.android.study.commonres.widget.dialog

import android.content.Context
import com.example.android.study.commonres.R
import com.example.android.study.commonres.widget.base.BaseCenterPopupView

/**
 * 描述：
 *
 * @author JiaoPeng by 2020/7/30
 */
class LoadingViewDialog(context: Context) : BaseCenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.loading_view
    }



}