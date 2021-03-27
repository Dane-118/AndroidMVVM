package com.example.android.study.commonres.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 描述：  复制内容帮助类
 *
 * @author JiaoPeng by 2020/7/18
 */
class CopyLinkTextUtil private constructor(context: Context) {
    /**
     * 复制文字到剪切板
     * @param text
     */
    fun CopyText(text: String?) {
        text?.let {
            // 创建能够存入剪贴板的ClipData对象
            //‘Label’这是任意文字标签
            val mClipData = ClipData.newPlainText("Label", text)
            //将ClipData数据复制到剪贴板：
            manager.setPrimaryClip(mClipData)
        }

    }

    /**
     * 复制链接url到剪切板
     * @param url
     */
    fun CopyUrl(url: String?) {
        url?.let {
            //‘Label’这是任意文字标签
            val mClipData = ClipData.newRawUri("Label", Uri.parse(url))
            //将ClipData数据复制到剪贴板：
            manager.setPrimaryClip(mClipData)
        }

    }

    /**
     * 复制Intent到剪切板
     * @param intent
     */
    fun CopyIntent(intent: Intent?) {
        intent?.let {
            //‘Label’这是任意文字标签
            val mClipData = ClipData.newIntent("Label", intent)
            //将ClipData数据复制到剪贴板：
            manager.setPrimaryClip(mClipData)
        }

    }

    /**
     *
     * 从剪贴板中获取数据,如text文字，链接等，
     * @return
     */
    val copyString: String?
        get() {
            val clipData = manager.primaryClip
            return if (clipData != null && clipData.itemCount > 0) {
                clipData.getItemAt(0).text?.toString()
            } else null
        }

    companion object {
        private var instance: CopyLinkTextUtil? = null
        private lateinit var manager: ClipboardManager

        @Synchronized
        fun getInstance(context: Context): CopyLinkTextUtil{
            if (instance == null) {
                instance = CopyLinkTextUtil(context)
            }
            return instance!!
        }
    }

    init {
        //获取剪贴板管理器：
        manager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
}