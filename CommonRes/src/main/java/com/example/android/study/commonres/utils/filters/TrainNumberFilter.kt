package com.example.android.study.commonres.utils.filters

import android.text.LoginFilter

/**
 * 描述：限制输入框只能输入大写字母和数字
 *
 * @author JiaoPeng by 2020/8/5
 */
class TrainNumberFilter : LoginFilter.UsernameFilterGeneric() {

    override fun isAllowed(c: Char): Boolean {
        if (c in '0'..'9')
            return true
        if (c in 'A'..'Z')
            return true
        return false
    }

}