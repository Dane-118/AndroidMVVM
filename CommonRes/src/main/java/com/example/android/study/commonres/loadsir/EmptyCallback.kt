package com.example.android.study.commonres.loadsir

import com.example.android.study.commonres.R
import com.kingja.loadsir.callback.Callback

class EmptyCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_empty
    }


}