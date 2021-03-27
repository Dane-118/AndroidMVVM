package com.example.android.study.commonres.ext

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.StringUtils

/**
 * 描述：
 *
 * @author {Wang Peng} by 1/28/21
 */
fun Int.getDrawable(): Drawable = ResourceUtils.getDrawable(this)


fun Int.getString(): String = StringUtils.getString(this)


fun Int.getColor(): Int = ColorUtils.getColor(this)




