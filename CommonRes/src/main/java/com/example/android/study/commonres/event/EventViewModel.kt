package com.gaolvgo.train.commonres.event

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.livedata.UnPeekLiveData

/**
 * 作者　: hegaojian
 * 时间　: 2019/5/2
 * 描述　:APP全局的ViewModel，可以在这里发送全局通知替代EventBus，LiveDataBus等
 */
class EventViewModel : BaseViewModel() {


    /**
     * 是否登录
     */
    var loginSuccess = UnPeekLiveData<Boolean>()
    /**3/8/21  12306 登录成功*/
    var login12306Success = UnPeekLiveData<Boolean>()





}