package com.example.android.study.commonres.utils.filters

import android.text.InputFilter
import android.text.Spanned

/**
 * 描述：
 *
 * @author JiaoPeng by 2020/7/17
 */
class SpaceFilter : InputFilter {

    override fun filter(source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        return if (source!! == " " || source.toString().contentEquals("\n")) {
            ""
        } else {
            null
        }
    }

}