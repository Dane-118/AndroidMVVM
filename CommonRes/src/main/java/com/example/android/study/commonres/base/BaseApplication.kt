package com.example.android.study.commonres.base

import android.app.Application
import android.content.Context
import cody.bus.ElegantBus
import com.alibaba.android.arouter.launcher.ARouter
import com.baidu.mapapi.CoordType
import com.baidu.mapapi.SDKInitializer
import com.blankj.utilcode.util.SizeUtils
import com.chuanglan.shanyan_sdk.OneKeyLoginManager
import com.clj.fastble.BleManager
import com.example.android.study.commonres.loadsir.EmptyCallback
import com.example.android.study.commonres.loadsir.ErrorCallback
import com.example.android.study.commonres.loadsir.LoadingCallback
import com.example.android.study.commonres.network.AppConstant
import com.example.android.study.commonres.widget.view.ClassicsHeader
import com.example.android.study.commonres.BuildConfig
import com.example.android.study.push.XPush
import com.example.android.study.push.jpush.JPushClient
import com.hm.lifecycle.api.ApplicationLifecycleManager
import com.ishumei.smantifraud.SmAntiFraud
import com.kingja.loadsir.core.LoadSir
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.tencent.mmkv.MMKV
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig
import com.xiaomi.mipush.sdk.MiPushClient
import me.hgj.jetpackmvvm.base.BaseApp
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.ext.util.jetpackMvvmLog
import me.hgj.jetpackmvvm.ext.util.loge


class BaseApplication : BaseApp() {


    override fun onCreate() {
        super.onCreate()
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (BuildConfig.DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this)
        ApplicationLifecycleManager.init()
        ApplicationLifecycleManager.onCreate(this)
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ -> ClassicsHeader(context) }
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ -> ClassicsFooter(context).setDrawableSize(SizeUtils.px2sp(20f).toFloat()); }
        initLocal()
        ElegantBus.setDebug(true)// 可以打开日志开关
        initBlue()

        /**友盟 */
        initUmeng(this)
        /**3/15/21 地图 */
        initMap(this)

        initShanyanSDK(this)

        /**风控 */
        initSmAntiFraud(this)



    }


    private fun initShanyanSDK(context: Context) {
        //闪验SDK配置debug开关 （必须放在初始化之前，开启后可打印闪验SDK更加详细日志信息）
        OneKeyLoginManager.getInstance().setDebug(BuildConfig.DEBUG)

        OneKeyLoginManager.getInstance().init(context, "C7n1yzcb") { _, _ -> //闪验SDK初始化结果回调
        }
    }

    private fun initLocal() {
        /**
         * mmkv本地存储
         */
        jetpackMvvmLog = BuildConfig.DEBUG

        MMKV.initialize(this)

        //小米推送
        MiPushClient.getRegId(this)
        //界面加载管理 初始化
        LoadSir.beginBuilder()
                .addCallback(LoadingCallback())//加载
                .addCallback(ErrorCallback())//错误
                .addCallback(EmptyCallback())//空
                .setDefaultCallback(LoadingCallback::class.java)//设置默认加载状态页
                .commit()
        //防止项目崩溃，崩溃后打开错误界面
        /*  CaocConfig.Builder.create()
                  .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //default: CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM
                  .enabled(true)//是否启用CustomActivityOnCrash崩溃拦截机制 必须启用！不然集成这个库干啥？？？
                  .showErrorDetails(false) //是否必须显示包含错误详细信息的按钮 default: true
                  .showRestartButton(false) //是否必须显示“重新启动应用程序”按钮或“关闭应用程序”按钮default: true
                  .logErrorOnRestart(false) //是否必须重新堆栈堆栈跟踪 default: true
                  .trackActivities(true) //是否必须跟踪用户访问的活动及其生命周期调用 default: false
                  .minTimeBetweenCrashesMs(2000) //应用程序崩溃之间必须经过的时间 default: 3000
                  .restartActivity(WelcomeActivity::class.java) // 重启的activity
                  .errorActivity(ErrorActivity::class.java) //发生错误跳转的activity
                  .apply()*/


        /**
         * 推送
         */
        XPush.debug(BuildConfig.DEBUG)
        //静态注册，指定使用友盟推送客户端
        //静态注册，指定使用友盟推送客户端
        XPush.init(this, JPushClient())
        XPush.register()



    }

    /**
     * 初始化蓝牙
     */
    private fun initBlue() {
        BleManager.getInstance().init(appContext)
        BleManager.getInstance()
                .apply {
                    enableLog(true)
                    reConnectCount = 0
                    connectOverTime = 10000
                    operateTimeout = 5000
                }
    }


    /**
     * 初始化友盟
     */
    private fun initUmeng(application: Application) {
        // TODO: 2020/11/2  友盟抛出了异常：crash并提示[SCH10003]请配置您的友盟Appkey，清单文件配置好像不行
        UMConfigure.init(application, "5df9d7364ca357c82c000f12", "UMeng", UMConfigure.DEVICE_TYPE_PHONE, "")
        
    }

    /**
     * 百度地图
     */
    private fun initMap(application: Application) {
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(application)
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL)
    }


    /**
     * 配置数美风控
     */
    private fun initSmAntiFraud(application: Application) {
        val option = SmAntiFraud.SmOption()
        // 通用配置项
        option.apply {
            //必填，组织标识，邮件中 organization 项
            organization = AppConstant.SM_ORGANIZATION
            //必填，应用标识，登录数美后台应用管理查看
            appId = AppConstant.SM_APPID
            //必填，加密 KEY，邮件中 android_public_key 附件内容
            publicKey = AppConstant.SM_PUBLICKEY
            //必填，加密 KEY，邮件中 Android ainfo key 项
            ainfoKey = AppConstant.SM_AINFOKEY
        }

        //连接机房特殊配置项
        //用户分布：中国（默认设置）
        option.area = SmAntiFraud.AREA_BJ
//        option.area = "8E9h1sW6OluQR0VMZf8b"
        //请求地址
//        val host = "http://fp-it-acc.fengkongcloud.com"
//        option.url = host + "/v3/profile/android"
//        option.confUrl = host + "/v3/cloudconf"

        //SDK初始化
        SmAntiFraud.create(application, option)
        //判断数美监控配置是否成功
        SmAntiFraud.registerServerIdCallback(object : SmAntiFraud.IServerSmidCallback {
            override fun onSuccess(deviceId: String) {
                // 服务器或缓存中有可用 deviceId
                // 本地保存，调用接口上传  5次验证不通过，延时重新验证
                "deviceId = $deviceId".loge()
            }

            override fun onError(errorCode: Int) {
                "errorCode = $errorCode".loge()
                // 未获取 deviceId
                // errorCode 含义
                // -1: ERROR_NO_NETWORK，无网络（检查网络是否正常连接）
                // -2: ERROR_NO_RESPONSE，服务器无响应（检查接入是否有问题）
                // -3: ERROR_SERVER_RESPONSE，服务器响应错误（检查配置是否填写正确）
            }
        })

    }


    override fun onTerminate() {
        super.onTerminate()
        ApplicationLifecycleManager.onTerminate()
        ARouter.getInstance().destroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        ApplicationLifecycleManager.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        ApplicationLifecycleManager.onTrimMemory(level)
    }
}