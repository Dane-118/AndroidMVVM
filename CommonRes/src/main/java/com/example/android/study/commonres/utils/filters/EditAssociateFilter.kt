package com.example.android.study.commonres.utils.filters

import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan


/**
 * 描述：过滤非选中输入，比如输入中文时，过滤掉搜索框里已经输入的拼音（小米手机出现的情况）
 *      返回空字符串，就代表匹配不成功，返回null代表匹配成功
 *
 * @author JiaoPeng by 2020/8/21
 */
class EditAssociateFilter : InputFilter {

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        val ss = SpannableString(source)
        val spans = ss.getSpans(0, ss.length, Any::class.java)
        if (spans != null) {
            for (span in spans) {
                if (span is UnderlineSpan) {
                    return ""
                }
            }
        }
        return null
    }

}