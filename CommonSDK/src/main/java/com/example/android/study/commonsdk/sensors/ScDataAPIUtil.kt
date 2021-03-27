package com.example.android.study.commonsdk.sensors

import android.content.Context
import com.blankj.utilcode.util.AppUtils
import com.sensorsdata.analytics.android.sdk.SAConfigOptions
import com.sensorsdata.analytics.android.sdk.SensorsAnalyticsAutoTrackEventType
import com.sensorsdata.analytics.android.sdk.SensorsDataAPI
import org.json.JSONException
import org.json.JSONObject

/**
 * 描述：
 *
 * @author JiaoPeng by 3/13/21
 */
object ScDataAPIUtil {

    /**
     * 初始化神策埋点
     */
    fun initSensorsApi(context: Context, saServerUrl: String) {
        //初始化配置
        val saConfigOptions = SAConfigOptions(saServerUrl)
        //开启全埋点
        saConfigOptions.setAutoTrackEventType(SensorsAnalyticsAutoTrackEventType.APP_CLICK or
                SensorsAnalyticsAutoTrackEventType.APP_START or
                SensorsAnalyticsAutoTrackEventType.APP_END
        )
                //H5
                .enableJavaScriptBridge(true)
                // 开启可视化全埋点
                .enableLog(false)
        SensorsDataAPI.startWithConfigOptions(context, saConfigOptions)
        registerAllProperties()
        registerAllProperties("platform_type", "app")
        //神策埋点开启所有Fragment
        startAllFragment()
    }

    /**
     * 设置事件公共属性
     */
    fun registerAllProperties() {
        try {
            val properties = JSONObject()
            properties.put("AppName", AppUtils.getAppName())
            SensorsDataAPI.sharedInstance().registerSuperProperties(properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * 设置事件公共属性
     */
    fun registerAllProperties(keyName: String, value: String) {
        try {
            val properties = JSONObject()
            properties.put(keyName, value)
            SensorsDataAPI.sharedInstance().registerSuperProperties(properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * 用户登陆
     */
    fun sensorsLogin(id: String) {
        SensorsDataAPI.sharedInstance().login(id)
    }

    /**
     * 自定义匿名ID
     */
    fun identify() {
        try {
            SensorsDataAPI.sharedInstance().identify(SensorsDataAPI.sharedInstance().distinctId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 记录激活事件--渠道路径
     */
    fun trackInstallation(channel: String) {
        try {
            val properties = JSONObject()
            //这里的 DownloadChannel 负责记录下载商店的渠道，值应传入具体应用商店包的标记。如果没有为不同商店打多渠道包，则可以忽略该属性的代码示例。
            properties.put("DownloadChannel", channel)
            // 触发激活事件
            SensorsDataAPI.sharedInstance().trackInstallation("AppInstall", properties)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 代码埋点追踪事件
     */
    fun track(key: String, properties: JSONObject) {
        try {
            SensorsDataAPI.sharedInstance().track(key, properties)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    /**
     * 开启全部 Fragment 页面浏览事件的自动采集功能
     */
    fun startAllFragment() {
        // 初始化 SDK 之后，开启自动采集 Fragment 页面浏览事件
        SensorsDataAPI.sharedInstance().trackFragmentAppViewScreen();
    }

    /**
     * 运营位点击埋点
     */
    fun bannerSensorsData(bannerBean: ScBannerClickBean) {
        val properties = JSONObject()
        properties.put("page_type", bannerBean.page_type)
        properties.put("banner_belong_area", bannerBean.banner_belong_area)
        properties.put("banner_name", bannerBean.banner_name)
        properties.put("banner_rank", bannerBean.banner_rank)
        properties.put("banner_id", bannerBean.banner_id)
        properties.put("activity_name", bannerBean.activity_name)
        properties.put("activity_id", bannerBean.activity_id)
        properties.put("url", bannerBean.url)
        properties.put("city_id", bannerBean.city_id)
        properties.put("city_name", bannerBean.city_name)
        track("BannerClick", properties)
    }

    /**
     * 悬浮点击埋点
     */
    fun floatSensorsData(floatBean: ScFloatClickBean) {
        val properties = JSONObject()
        properties.put("page_type", floatBean.page_type)
        properties.put("banner_name", floatBean.banner_name)
        properties.put("banner_id", floatBean.banner_id)
        properties.put("activity_name", floatBean.activity_name)
        properties.put("activity_id", floatBean.activity_id)
        properties.put("url", floatBean.url)
        properties.put("city_id", floatBean.city_id)
        properties.put("city_name", floatBean.city_name)
        track("FloatClick", properties)
    }

    /**
     * 弹窗点击埋点
     */
    fun popSensorsData(popBean: ScPopClickBean) {
        val properties = JSONObject()
        properties.put("page_type", popBean.page_type)
        properties.put("banner_name", popBean.banner_name)
        properties.put("banner_id", popBean.banner_id)
        properties.put("activity_name", popBean.activity_name)
        properties.put("activity_id", popBean.activity_id)
        properties.put("url", popBean.url)
        properties.put("city_id", popBean.city_id)
        properties.put("city_name", popBean.city_name)
        track("PopupClick", properties)
    }

    /**
     * 开机大图点击埋点
     */
    fun openScreamSensorsData(openScreamClickBean: ScOpenScreamClickBean) {
        val properties = JSONObject()
        properties.put("banner_name", openScreamClickBean.banner_name)
        properties.put("banner_id", openScreamClickBean.banner_id)
        properties.put("activity_name", openScreamClickBean.activity_name)
        properties.put("activity_id", openScreamClickBean.activity_id)
        properties.put("url", openScreamClickBean.url)
        properties.put("city_id", openScreamClickBean.city_id)
        properties.put("city_name", openScreamClickBean.city_name)
        track("OpenScreamClick", properties)
    }

    /**
     * 城市设置埋点
     */
    fun citySensorsData(cityBean: ScCityBean) {
        val properties = JSONObject()
        properties.put("type", cityBean.type)
        properties.put("city_id", cityBean.city_id)
        properties.put("city_name", cityBean.city_name)
        track("CitySetting", properties)
    }

    /**
     * 消息通知开关设置埋点
     * 开启/关闭
     */
    fun messageSensorsData(type: String) {
        val properties = JSONObject()
        properties.put("type", type)
        track("MessageSetting", properties)
    }

}