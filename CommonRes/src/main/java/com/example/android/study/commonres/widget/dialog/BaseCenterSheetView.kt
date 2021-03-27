package com.example.android.study.commonres.widget.dialog

import android.content.Context
import android.text.Html
import android.text.TextUtils
import android.widget.TextView
import com.example.android.study.commonres.R
import com.example.android.study.commonres.bean.BasePopViewEntry
import com.example.android.study.commonres.ext.text
import com.example.android.study.commonres.widget.base.BaseCenterPopupView
import com.lxj.xpopup.util.XPopupUtils
import kotlinx.android.synthetic.main.public_dialog_center.view.*
import me.hgj.jetpackmvvm.ext.view.gone
import me.hgj.jetpackmvvm.ext.view.visible

/**
 * 描述：车票支付的Dialog
 *
 * @author JiaoPeng by 2020/7/25
 */
class BaseCenterSheetView(context: Context,
                          private val basePopViewEntry: BasePopViewEntry
) : BaseCenterPopupView(context) {

    override fun getImplLayoutId(): Int {
        return R.layout.public_dialog_center
    }

    override fun onCreate() {
        super.onCreate()
        if (TextUtils.isEmpty(basePopViewEntry.leftText)) {
            findViewById<TextView>(R.id.btn_dialog_left)
            findViewById<TextView>(R.id.btn_dialog_left)?.gone()
        } else {
            btn_dialog_left?.visible()
            btn_dialog_left.text(basePopViewEntry.leftText)
        }

        if (TextUtils.isEmpty(basePopViewEntry.rightText)) {
            btn_dialog_right?.gone()
        } else {
            btn_dialog_right?.visible()
            btn_dialog_right.text(basePopViewEntry.rightText)
        }
        if (TextUtils.isEmpty(basePopViewEntry.singleText)) {
            btn_dialog_single?.gone()
        } else {
            btn_dialog_single?.visible()
            btn_dialog_single.text(basePopViewEntry.singleText)
        }

        if (basePopViewEntry.drawable == null) {
            iv_dialog_icon?.gone()
        } else {
            iv_dialog_icon?.visible()
            iv_dialog_icon.setImageDrawable(basePopViewEntry.drawable)
        }
        btn_dialog_left.setOnClickListener {
            dismiss()
            basePopViewEntry.leftClickListener?.invoke()
        }
        btn_dialog_right.setOnClickListener {
            dismiss()
            basePopViewEntry.rightClickListener?.invoke()
        }
        btn_dialog_single.setOnClickListener {
            basePopViewEntry.singleClickListener?.invoke()
            dismiss()
        }
        if (basePopViewEntry.isHtml) {
            tv_dialog_message.text = Html.fromHtml(basePopViewEntry.messageText)

        } else {
            tv_dialog_message.text = basePopViewEntry.messageText

        }

        customer_title.text(basePopViewEntry.titleText)
    }

    override fun getMaxWidth(): Int {
        return (XPopupUtils.getWindowWidth(context) * 0.9f).toInt()
    }


}