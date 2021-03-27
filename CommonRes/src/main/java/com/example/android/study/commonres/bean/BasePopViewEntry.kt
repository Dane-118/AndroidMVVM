package com.example.android.study.commonres.bean

import android.graphics.drawable.Drawable
import com.example.android.study.commonres.R

/**
 * 描述：
 *
 * @author {Wang Peng} by 2/1/21
 */
open class BasePopViewEntry(
        open var titleText: String? = null,
        open var drawable: Drawable? = null,
        open var messageText: String? = null,
        open var messageTextColor: Int? = null,
        open var leftText: String? = null,
        open var rightText: String? = null,
        open var singleText: String? = null,
        open var leftTextColor: Int = R.color.c_909399,
        open var rightTextColor: Int = R.color._007aff,
        open var isHtml: Boolean = false,
        open var leftClickListener: (() -> Unit)? = null,
        open var singleClickListener: (() -> Unit)? = null,
        open var rightClickListener: (() -> Unit)? = null,
)

open class StratifiedBean(
        override var messageText: String? = null,
        var itemClickListener: ((Int) -> Unit)? = null,
        /**2/3/21 dialog 数据 */
        val listData: ArrayList<String> = arrayListOf(),
) : BasePopViewEntry()

open class DoneInvokeStringArrayList(
        val doneClickListener: ((ArrayList<String>) -> Unit)
) : BasePopViewEntry()

open class DoneInvokeInt(
        val doneClickListener: ((Int) -> Unit)
) : BasePopViewEntry()















