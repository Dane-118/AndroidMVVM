package com.example.android.study.commonres.widget.dialog

import android.content.Context
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils.getColor
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.StringUtils
import com.example.android.study.commonres.R
import com.example.android.study.commonres.bean.StratifiedBean
import com.example.android.study.commonres.widget.base.BaseCenterPopupView
import kotlinx.android.synthetic.main.dialog_center_user_agreement.view.*

/**

@author fangdongdong
@description:
@date : 2020/8/19 14:21
 */
class UserAgreementDialog(context: Context,
                          val basePopViewEntry: StratifiedBean) : BaseCenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.dialog_center_user_agreement
    }

    override fun onCreate() {
        super.onCreate()
        SpanUtils.with(tv_user_agreement)
                .append(StringUtils.getString(R.string.user_agreement_one))
                .append(StringUtils.getString(R.string.tv_start_h5))
                .setClickSpan(getColor(R.color.color_text_press), false) {
                    basePopViewEntry.itemClickListener?.invoke(1)
                }
                .append(StringUtils.getString(R.string.with))
                .append(StringUtils.getString(R.string.login_privacy_pacy))
                .setClickSpan(getColor(R.color.color_text_press), false) {
                    basePopViewEntry.itemClickListener?.invoke(2)
                }
                .append(context.getString(R.string.user_agreement_two))
                .create()

        findViewById<TextView>(R.id.btn_user_agreement).setOnClickListener {
            smartDismiss()
            basePopViewEntry.itemClickListener?.invoke(3)
        }
    }


}