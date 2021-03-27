package com.example.android.study.commonres.utils.filters

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 描述：禁止输入框输入表情
 *
 * @author JiaoPeng by 1/27/21
 */
class EmojiFilter : InputFilter {

    var emoji: Pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
            Pattern.UNICODE_CASE or Pattern.CASE_INSENSITIVE)

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        val emojiMatcher: Matcher = emoji.matcher(source)
        if (emojiMatcher.find()) {
            return ""
        }
        return null
    }

}