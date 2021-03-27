package com.example.android.study.commonres.loadsir

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.android.study.commonres.R
import com.kingja.loadsir.callback.Callback

class LoadingCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onViewCreate(context: Context, view: View) {
        super.onViewCreate(context, view)
        val gifImage = view.findViewById<ImageView>(R.id.web_loading)
        Glide.with(context).asGif().load(R.drawable.loading_bg).centerCrop().into(gifImage)
    }
}