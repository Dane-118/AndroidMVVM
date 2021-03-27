package com.example.android.study.commonres.widget.dialog

import android.content.Context
import android.text.Html
import android.text.TextUtils
import android.view.View
import com.example.android.study.commonres.R
import com.example.android.study.commonres.bean.BasePopViewEntry
import com.example.android.study.commonres.utils.onClick
import com.example.android.study.commonres.widget.base.BaseCenterPopupView
import kotlinx.android.synthetic.main.phone_num_verify_center_dialog.view.*

/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/8/3
 */
class PhoneNumVerifyCenterSheetView(context: Context,
                                    private val basePopViewEntry: BasePopViewEntry
) : BaseCenterPopupView(context) {


    override fun getImplLayoutId(): Int {
        return R.layout.phone_num_verify_center_dialog
    }

    override fun onCreate() {
        super.onCreate()

        if (TextUtils.isEmpty(basePopViewEntry.leftText)) {
            confirm?.visibility = View.GONE
            tv_know?.visibility = View.VISIBLE
            tv_know?.text = basePopViewEntry.singleText
        } else {
            confirm?.visibility = View.VISIBLE
            tv_know?.visibility = View.GONE
            tv_cancel?.text = basePopViewEntry.leftText
            tv_confirm?.text = basePopViewEntry.rightText
        }

        basePopViewEntry.titleText?.let {
            tv_title?.text = it
        }


        basePopViewEntry.messageText?.let {
            tv_message?.text = Html.fromHtml(it)

        }

        basePopViewEntry.drawable?.let {
            iv?.setImageDrawable(it)

        }

        tv_cancel?.onClick {
            basePopViewEntry.leftClickListener?.invoke()
            dismiss()
        }

        tv_confirm.onClick {
            basePopViewEntry.rightClickListener?.invoke()
            dismiss()
        }
        tv_know?.onClick {
            dismiss()
        }

    }


}