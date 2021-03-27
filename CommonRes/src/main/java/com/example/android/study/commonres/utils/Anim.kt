/*
 * Copyright 2018 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.study.commonres.utils

import android.app.Activity
import android.view.View
import android.widget.ImageView
import com.example.android.study.commonres.R

/**
 * ================================================
 * 动画工具类
 *
 *
 * Created by JessYan on 27/04/2017 21:40
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
object Anim {
    fun exit(obj: Activity) {
        obj.overridePendingTransition(R.anim.public_translate_left_to_center, R.anim.public_translate_center_to_right)
    }

    fun `in`(obj: Activity) {
        obj.overridePendingTransition(R.anim.public_translate_right_to_center, R.anim.public_translate_center_to_left)
    }

    fun cleanAnim(animView: ImageView?) {
        if (animView == null) return
        animView.setImageResource(0)
        animView.clearAnimation()
        animView.visibility = View.GONE
    }
}