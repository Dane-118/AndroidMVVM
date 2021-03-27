package com.example.android.study.commonres.base

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.alibaba.android.arouter.launcher.ARouter
import com.example.android.study.commonres.R
import com.gaolvgo.train.commonres.event.AppViewModel
import com.gaolvgo.train.commonres.event.EventViewModel
import com.gyf.immersionbar.ImmersionBar
import me.hgj.jetpackmvvm.base.activity.BaseVmActivity
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.getAppViewModel

/**
 * 描述　: 你项目中的Activity基类，在这里实现显示弹窗，吐司，还有加入自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmActivity例如
 * abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
 */
abstract class BaseActivity<VM : BaseViewModel> : BaseVmActivity<VM>() {
    var TAG: String = javaClass.name
    //Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel() }

    //Application全局的ViewModel，用于发送全局通知操作
    val eventViewModel: EventViewModel by lazy { getAppViewModel() }

    abstract override fun layoutId(): Int


     override fun initView(savedInstanceState: Bundle?){
         findViewById<Toolbar>(R.id.public_toolbar)?.let {
             ImmersionBar.with(this)
                     .titleBar(it)
                     .statusBarDarkFont(true, 0.2f)
                     .navigationBarColor(R.color.transparent)
                     .init()
         }
         ARouter.getInstance().inject(this)


     }

    /**
     * 创建liveData观察者
     */
//    override fun createObserver() {}

    /**
     * 打开等待框
     */
    override fun showLoading(message: String) {

    }

    /**
     * 关闭等待框
     */
    override fun dismissLoading() {

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    /* */
    /**
     * 在任何情况下本来适配正常的布局突然出现适配失效，适配异常等问题，只要重写 Activity 的 getResources() 方法
     *//*
    override fun getResources(): Resources {
        AutoSizeCompat.autoConvertDensityOfGlobal(super.getResources())
        return super.getResources()
    }*/
}