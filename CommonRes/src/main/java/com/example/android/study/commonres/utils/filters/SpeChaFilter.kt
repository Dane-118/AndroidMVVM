package com.example.android.study.commonres.utils.filters

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 描述：禁止输入框输入特殊字符
 *
 * @author JiaoPeng by 1/27/21
 */
class SpeChaFilter : InputFilter {

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        val speChat = "[`~!@#_$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）— +|{}【】‘；：”“’。，、？]"
        val pattern: Pattern = Pattern.compile(speChat)
        val matcher: Matcher = pattern.matcher(source.toString())
        return if (matcher.find()) "" else null
    }

}