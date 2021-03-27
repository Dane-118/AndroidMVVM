package com.example.android.study.commonres.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.CompoundButton
import com.blankj.utilcode.util.PermissionUtils
import com.blankj.utilcode.util.StringUtils.getString
import com.blankj.utilcode.util.ToastUtils
import com.example.android.study.commonres.R
import com.example.android.study.commonres.bean.BasePopViewEntry
import com.example.android.study.commonres.widget.dialog.CustomerPopView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.SimpleCallback

/**
 * 描述：
 *
 * @author {Wang Peng} by 2020/10/26
 */


/***
 * 点击事件的View扩展
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.onClick(block: (T) -> Unit) = setOnClickListener {
    if (DoubleClickUtils.isValid(this, 800)) {
        block(it as T)
    }
}

fun <T : CompoundButton> T.onChangeClick(block: (buttonView: View, isCheck: Boolean) -> Unit) = setOnCheckedChangeListener { buttonView, isChecked ->
    if (DoubleClickUtils.isValid(this)) {
        block(buttonView, isChecked)
    }
}


fun BasePopupView.showPopupView(context: Context,
                                dismissOnTouchOutside: Boolean = false,
                                dismissOnBackPressed: Boolean = false,
                                moveUpToKeyboard: Boolean = false,
                                hasShadowBg: Boolean = true,
                                isDestroyOnDismiss: Boolean = false,
                                isDrag: Boolean = false,
                                onDismiss: (() -> Unit)? = null): BasePopupView = XPopup.Builder(context)
        .setPopupCallback(object : SimpleCallback() {
            override fun onDismiss(popupView: BasePopupView?) {
                super.onDismiss(popupView)
                onDismiss?.invoke()
            }

        })
        .dismissOnTouchOutside(dismissOnTouchOutside)
        .dismissOnBackPressed(dismissOnBackPressed)
        .hasShadowBg(hasShadowBg)
        .enableDrag(isDrag)
        .moveUpToKeyboard(moveUpToKeyboard)
        .isDestroyOnDismiss(isDestroyOnDismiss)
        .asCustom(this)


fun BasePopupView.showPopupView(
        context: Context,
        view: View,
        dismissOnTouchOutside: Boolean = true,
        dismissOnBackPressed: Boolean = false,
        moveUpToKeyboard: Boolean = false,
        hasShadowBg: Boolean = true,
        isDrag: Boolean = false,
        onDismiss: (() -> Unit)? = null): BasePopupView = XPopup.Builder(context)
        .setPopupCallback(object : SimpleCallback() {
            override fun onDismiss(popupView: BasePopupView?) {
                super.onDismiss(popupView)
                onDismiss?.invoke()
            }

        })
        .dismissOnTouchOutside(dismissOnTouchOutside)
        .dismissOnBackPressed(dismissOnBackPressed)
        .moveUpToKeyboard(moveUpToKeyboard)
        .hasShadowBg(hasShadowBg)
        .enableDrag(isDrag)
        .atView(view)
        .asCustom(this)


/**
 * 权限处理
 */
fun onRequestPermissionFailurePopView(permissions: List<String>?) {
    permissions?.let {
        val permissionNames = Permission.transformText(permissions)
        val message = getString(R.string.message_permission_rationale, TextUtils.join("\n", permissionNames))
        ToastUtils.showShort(message)
    }
}

/**
 * 权限处理 ,跳转设置
 */
fun onRequestPermissionFailureWithAskNeverAgainPopView(context: Context, permissions: List<String>?) {
    permissions?.let {
        val permissionNames = Permission.transformText(permissions)
        val message = getString(R.string.message_permissions, TextUtils.join("\n", permissionNames))
        CustomerPopView(
                context,
                basePopViewEntry = BasePopViewEntry(
                        titleText = "温馨提示",
                        messageText = message,
                        leftText = "取消",
                        rightText = "确定",
                        singleClickListener = {
                            PermissionUtils.launchAppDetailsSettings()
                        }
                )

        )
                .showPopupView(context).toggle()

    }
}