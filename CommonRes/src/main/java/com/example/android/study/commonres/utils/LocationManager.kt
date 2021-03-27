package com.example.android.study.commonres.utils

import android.content.Context
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.baidu.location.BDAbstractLocationListener
import com.baidu.location.BDLocation
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption


/**
 * @author WangPeng
 * @date 2020/5/18
 */

class LocationManager(context: Context, private val mLifecycleOwner: LifecycleOwner) : DefaultLifecycleObserver {

    private val mLocationClient: LocationClient by lazy {
        val locationClient = LocationClient(context)
        locationClient.locOption = defaultLocationClientOption
        locationClient
    }

    private val myBDAbstractLocationListener: MyBDAbstractLocationListener by lazy {
        MyBDAbstractLocationListener()
    }

    init {
        mLifecycleOwner.lifecycle.addObserver(this)
    }

    /**
     *
     * @return DefaultLocationClientOption 默认O设置
     */
    private val defaultLocationClientOption: LocationClientOption
        get() {
            val mOption = LocationClientOption()
            mOption.locationMode = LocationClientOption.LocationMode.Hight_Accuracy // 可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll") // 可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            // mOption.setScanSpan(0); // 可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true) // 可选，设置是否需要地址信息，默认不需要
            // mOption.setIsNeedLocationDescribe(true); // 可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false) // 可选，设置是否需要设备方向结果
            mOption.isLocationNotify = false // 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(true) // 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop
            mOption.setIsNeedLocationDescribe(true) // 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation
            mOption.setIsNeedLocationPoiList(true) // 可选，默认false，设置是否需要POI结果，可以在BDLocation
            mOption.SetIgnoreCacheException(false) // 可选，默认false，设置是否收集CRASH信息，默认收集
            mOption.isOpenGps = true // 可选，默认false，设置是否开启Gps定位
            mOption.setIsNeedAltitude(false) // 可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
            // 可选，设置是否需要最新版本的地址信息。默认需要，即参数为true
            mOption.setNeedNewVersionRgc(true)
            return mOption
        }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun startService(onLocation: ((BDLocation) -> Unit)) {
        Log.d("TAG", "startService: ")
        if (!mLocationClient.isStarted) {
            mLocationClient.start()
        } else {
            mLocationClient.restart()
        }
        mLocationClient.registerLocationListener(myBDAbstractLocationListener)
        myBDAbstractLocationListener.onLocation = {
            onLocation.invoke(it)
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopService() {
        Log.d("TAG", "stopService: ")
        if (mLocationClient.isStarted) {
            mLocationClient.stop()
            mLocationClient.unRegisterLocationListener(myBDAbstractLocationListener)
        }
    }

    inner class MyBDAbstractLocationListener : BDAbstractLocationListener() {
        var onLocation: ((BDLocation) -> Unit)? = null
        override fun onReceiveLocation(p0: BDLocation?) {
            p0?.let {
                Log.d("TAG", "onReceiveLocation:${p0.toString()} ")
                onLocation?.invoke(p0)
            }

        }

    }


}