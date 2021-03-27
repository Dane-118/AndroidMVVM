package com.example.android.study.commonres.base

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.KeyboardUtils
import com.example.android.study.commonres.R
import com.gaolvgo.train.commonres.event.AppViewModel
import com.gaolvgo.train.commonres.event.EventViewModel
import com.gyf.immersionbar.ImmersionBar
import me.hgj.jetpackmvvm.base.fragment.BaseVmFragment
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.getAppViewModel

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/21
 * 描述　: 你项目中的Fragment基类，在这里实现显示弹窗，吐司，还有自己的需求操作 ，如果不想用Databind，请继承
 * BaseVmFragment例如
 * abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {
 */
abstract class BaseFragment<VM : BaseViewModel> : BaseVmFragment<VM>() {

    val TAG:String = this.javaClass.name

    //Application全局的ViewModel，里面存放了一些账户信息，基本配置信息等
    val appViewModel: AppViewModel by lazy { getAppViewModel() }

    //Application全局的ViewModel，用于发送全局通知操作
    val eventViewModel: EventViewModel by lazy { getAppViewModel() }

    /**
     * 当前Fragment绑定的视图布局
     */
    abstract override fun layoutId(): Int


    override fun initView(savedInstanceState: Bundle?) {
        rootView?.findViewById<Toolbar>(R.id.public_toolbar)?.let {
            ImmersionBar.with(requireActivity())
                    .titleBar(it)
                    .statusBarDarkFont(true, 0.2f)
                    .navigationBarColor(R.color.transparent)
                    .init()
        }
        ARouter.getInstance().inject(this)
    }

    /**
     * 懒加载 只有当前fragment视图显示时才会触发该方法
     */
    override fun lazyLoadData() {}

    /**
     * 创建LiveData观察者 Fragment执行onViewCreated后触发
     */
    override fun createObserver() {}

    /**
     * Fragment执行onViewCreated后触发
     */
    override fun initData() {

    }

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

    override fun onPause() {
        super.onPause()
        activity?.let { KeyboardUtils.hideSoftInput(it) }
    }
}