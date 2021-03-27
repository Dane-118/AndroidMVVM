package com.example.android.study.commonres.shanyan

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.SizeUtils
import com.chuanglan.shanyan_sdk.tool.ShanYanUIConfig
import com.example.android.study.commonres.R

/**
 * @author fangdongdong
 * @description:闪演
 * @date : 2020/10/15 16:09
 */
object ShanYanUtils {
    // 沉浸式竖屏样式
    fun getCJSConfig(context: Context, onBackCallBack: () -> Unit, otherCallBack: () -> Unit): ShanYanUIConfig {
        /************************************************
         * 自定义控件
         */


        val logBtnImgPath = ResourceUtils.getDrawable(R.drawable.public_login_btn_code_enable)
        val checkImg = ResourceUtils.getDrawable(R.drawable.public_cb_selected)
        val unCheckImg = ResourceUtils.getDrawable(R.drawable.public_cb_un_selected)
        val logo = ResourceUtils.getDrawable(R.drawable.public_log_sdk_icon)
        // loading自定义加载框
        val inflater = LayoutInflater.from(context)
        val view_dialog = inflater.inflate(R.layout.public_shanyan_demo_dialog_layout, null) as RelativeLayout
        val mLayoutParams3 = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        view_dialog.layoutParams = mLayoutParams3
        view_dialog.visibility = View.GONE
        val titleinflater = LayoutInflater.from(context)
        val titleLayout = titleinflater.inflate(R.layout.public_shanyan_demo_head, null) as RelativeLayout
        val titleLayoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        titleLayoutParams.setMargins(0, 0, 0, 0)
        titleLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        titleLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        titleLayout.layoutParams = titleLayoutParams
        titleLayout.findViewById<View>(R.id.rl_log_back_sdk).setOnClickListener {
            onBackCallBack.invoke()
            //                Intent intent = new Intent(context, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra(FLAG_BLE, 1);
//                context.startActivity(intent);
        }
        val inflater1 = LayoutInflater.from(context)
        val relativeLayout = inflater1.inflate(R.layout.public_shanyan_demo_other_login_item, null) as RelativeLayout
        val layoutParamsOther = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
        layoutParamsOther.setMargins(0, SizeUtils.dp2px(285f), 0, 0)
        layoutParamsOther.addRule(RelativeLayout.CENTER_HORIZONTAL)
        layoutParamsOther.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        relativeLayout.layoutParams = layoutParamsOther
        AppUtils.getAppName()
        relativeLayout.findViewById<View>(R.id.log_other_sdk).setOnClickListener {
            otherCallBack.invoke()
            //                LoginOtherActivity.Companion.newInstance(context, 0);
        }
        return ShanYanUIConfig.Builder() // 授权页导航栏：
// .setActivityTranslateAnim("cp_push_bottom_in","translate_center_to_left")
                .setNavColor(Color.parseColor("#F19623")) // 设置导航栏颜色
                .setNavText("") // 设置导航栏标题文字
// .setAuthBGImgPath(backgruond)
                .setDialogDimAmount(0f)
                .setStatusBarHidden(false)
                .setNavReturnImgHidden(true) // 授权页号码栏：
                .setNumberColor(Color.parseColor("#171717")) // 设置手机号码字体颜色
                .setNumFieldOffsetY(130) // 设置号码栏相对于标题栏下边缘y偏移
                .setNumberSize(24)
                .setNumberBold(true)
                .setNumFieldHeight(50) // 授权页logo
                .setLogoImgPath(logo)
                .setLogoWidth(54)
                .setLogoHeight(54)
                .setLogoOffsetY(57) // 授权页登录按钮：
                .setLogBtnText("本机号码一键登录") // 设置登录按钮文字
                .setLogBtnTextColor(-0x1) // 设置登录按钮文字颜色
                .setLogBtnImgPath(logBtnImgPath) // 设置登录按钮图片
                .setLogBtnTextSize(16)
                .setLogBtnHeight(45)
                .setLogBtnOffsetY(235)
                .setLogBtnWidth(335) // 授权页slogan
                .setSloganHidden(false)
                .setSloganTextSize(12)
                .setSloganOffsetY(170)
                .setSloganTextColor(Color.parseColor("#ff868f95")) // 授权页隐私栏： file:///android_asset/user_pro.txt
                .setAppPrivacyOne("高旅纵横用户服务协议", "http://static.gaolvzongheng.com/agreement/service/index.html") // 设置开发者隐私条款1名称和URL(名称，url)
                .setAppPrivacyTwo("隐私协议", "http://static.gaolvzongheng.com/agreement/privacy/index.html") // 设置开发者隐私条款2名称和URL(名称，url)
                .setAppPrivacyColor(Color.parseColor("#3C3C3C"), Color.parseColor("#60C4FC")) // 设置隐私条款名称颜色(基础文字颜色，协议文字颜色)
                .setPrivacyText("同意", "和", "及", "、", "并授权高旅纵横获得本机号码")
                .setPrivacyOffsetBottomY(20) // 设置隐私条款相对于屏幕下边缘y偏
                .setPrivacyState(true)
                .setCheckedImgPath(checkImg)
                .setUncheckedImgPath(unCheckImg)
                .setPrivacyTextSize(10)
                .setPrivacyOffsetX(26)
                .setCheckBoxWH(12, 12)
                .setcheckBoxOffsetXY(0, 0)
                .setShanYanSloganTextColor(Color.parseColor("#ffffff"))
                .addCustomView(titleLayout, false, true, null)
                .setLoadingView(view_dialog) // 添加自定义控件:
                .addCustomView(relativeLayout, false, false, null) // 标题栏下划线，可以不写
                .build()
    }
}