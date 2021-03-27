package com.example.android.study.commonres.widget.dialog

import android.content.Context
import android.text.TextUtils
import com.example.android.study.commonres.R
import com.example.android.study.commonres.bean.BasePopViewEntry
import com.example.android.study.commonres.ext.getColor
import com.example.android.study.commonres.ext.text
import com.example.android.study.commonres.utils.onClick
import com.lxj.xpopup.core.CenterPopupView
import com.lxj.xpopup.util.XPopupUtils
import kotlinx.android.synthetic.main.public_customer_single_or_two_popview.view.*
import me.hgj.jetpackmvvm.ext.view.visibleOrGone

/**
 * 描述：行李卡删除确认的Dialog
 *
 * @author JiaoPeng by 2020/7/10
 */
class CustomerPopView(context: Context,
                      private val basePopViewEntry: BasePopViewEntry
) : CenterPopupView(context) {


    override fun getImplLayoutId(): Int {
        return R.layout.public_customer_single_or_two_popview
    }

    override fun onCreate() {
        super.onCreate()

        customer_title.visibleOrGone(!TextUtils.isEmpty(basePopViewEntry.titleText))
        customer_title.text(basePopViewEntry.titleText)
        customer_message.text(basePopViewEntry.messageText)

        basePopViewEntry.messageTextColor?.let {
            customer_message.setTextColor(it.getColor())
        }
        customer_message.visibleOrGone(!TextUtils.isEmpty(basePopViewEntry.messageText))
        customer_cancel.visibleOrGone(!TextUtils.isEmpty(basePopViewEntry.leftText))
        line_customer.visibleOrGone(!TextUtils.isEmpty(basePopViewEntry.leftText))

        customer_cancel.setTextColor(basePopViewEntry.leftTextColor.getColor())
        customer_sure.setTextColor(basePopViewEntry.rightTextColor.getColor())
        customer_cancel.text(basePopViewEntry.leftText)
        customer_cancel.onClick {
            dismiss()
        }

        customer_sure.text(basePopViewEntry.rightText)


        customer_sure.onClick {
            basePopViewEntry.rightClickListener?.invoke()
            dismiss()
        }

        customer_cancel.onClick {
            basePopViewEntry.leftClickListener?.invoke()
            dismiss()
        }

    }

    override fun getMaxWidth(): Int {
        return (XPopupUtils.getWindowWidth(context) * 8.5f).toInt()
    }

}