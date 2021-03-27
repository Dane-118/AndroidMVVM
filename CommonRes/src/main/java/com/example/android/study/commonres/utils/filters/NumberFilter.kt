package com.example.android.study.commonres.utils.filters

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 描述：禁止输入框输入数字
 *
 * @author JiaoPeng by 1/27/21
 */
class NumberFilter : InputFilter {

    var number: Pattern = Pattern.compile("[0-9]",
            Pattern.UNICODE_CASE or Pattern.CASE_INSENSITIVE)

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        val numberMatcher: Matcher = number.matcher(source)
        if (numberMatcher.find()) {
            return ""
        }
        return null
    }

}