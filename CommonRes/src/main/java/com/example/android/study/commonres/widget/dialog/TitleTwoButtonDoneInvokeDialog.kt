package com.example.android.study.commonres.widget.dialog

import android.content.Context
import com.blankj.utilcode.util.ColorUtils
import com.example.android.study.commonres.R
import com.example.android.study.commonres.bean.BasePopViewEntry
import com.lxj.xpopup.core.CenterPopupView
import com.lxj.xpopup.util.XPopupUtils
import kotlinx.android.synthetic.main.dialog_center_title_two_button_outsize_false.view.*

/**
 * 一个标题，两个按钮，点击外部不可取消
 *
 */

class TitleTwoButtonDoneInvokeDialog(context: Context,
                                     val basePopViewEntry: BasePopViewEntry
) : CenterPopupView(context) {


    override fun getImplLayoutId(): Int {
        return R.layout.dialog_center_title_two_button_outsize_false
    }

    override fun onCreate() {
        super.onCreate()

        tv_title?.text = basePopViewEntry.titleText
        tv_cancel?.text = basePopViewEntry.leftText
        tv_confirm?.text = basePopViewEntry.rightText

        tv_cancel.setTextColor(ColorUtils.getColor(basePopViewEntry.leftTextColor))
        tv_confirm.setTextColor(ColorUtils.getColor(basePopViewEntry.rightTextColor))


        tv_cancel?.setOnClickListener {
            basePopViewEntry.leftClickListener?.invoke()
            dismiss()
        }
        tv_confirm?.setOnClickListener {
            basePopViewEntry.rightClickListener?.invoke()
            dismiss()
        }


    }

    override fun getMaxWidth(): Int {
        return (XPopupUtils.getWindowWidth(context) * 8.4f).toInt()
    }
}