/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.study.commonsdk.permission

import android.Manifest
import com.tbruyelle.rxpermissions3.RxPermissions
import me.hgj.jetpackmvvm.util.LogUtils
import java.util.*

/**
 * ================================================
 * 权限请求工具类
 *
 * @see [PermissionUtil wiki 官方文档](https://github.com/JessYanCoding/MVPArms/wiki.3.9)
 * Created by JessYan on 17/10/2016 10:09
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
object PermissionUtil {
    interface RequestPermission {
        /**
         * 权限请求成功
         */
        fun onRequestPermissionSuccess()

        /**
         * 用户拒绝了权限请求, 权限请求失败, 但还可以继续请求该权限
         *
         * @param permissions 请求失败的权限名
         */
        fun onRequestPermissionFailure(permissions: List<String>)

        /**
         * 用户拒绝了权限请求并且用户选择了以后不再询问, 权限请求失败, 这时将不能继续请求该权限, 需要提示用户进入设置页面打开该权限
         *
         * @param permissions 请求失败的权限名
         */
        fun onRequestPermissionFailureWithAskNeverAgain(permissions: List<String>)
    }


    fun requestPermission(requestPermission: RequestPermission, rxPermissions: RxPermissions, vararg permissions: String) {
        if (permissions.isEmpty()) {
            return
        }
        val needRequest: MutableList<String> = ArrayList()
        for (permission in permissions) { //过滤调已经申请过的权限
            if (!rxPermissions.isGranted(permission)) {
                needRequest.add(permission)
            }
        }
        if (needRequest.isEmpty()) { //全部权限都已经申请过，直接执行操作
            requestPermission.onRequestPermissionSuccess()
        } else { //没有申请过,则开始申请
            rxPermissions
                    .requestEach(*needRequest.toTypedArray())
                    .buffer(permissions.size)
                    .subscribe({ permissions ->
                        val failurePermissions: ArrayList<String> = ArrayList()
                        val askNeverAgainPermissions: ArrayList<String> = ArrayList()
                        for (p in permissions) {
                            if (!p.granted) {
                                if (p.shouldShowRequestPermissionRationale) {
                                    failurePermissions.add(p.name)
                                } else {
                                    askNeverAgainPermissions.add(p.name)
                                }
                            }
                        }
                        if (failurePermissions.size > 0) {
                            LogUtils.debugLongInfo("Request permissions failure")
                            requestPermission.onRequestPermissionFailure(failurePermissions)
                        }
                        if (askNeverAgainPermissions.size > 0) {
                            LogUtils.debugLongInfo("Request permissions failure with ask never again")
                            requestPermission.onRequestPermissionFailureWithAskNeverAgain(askNeverAgainPermissions)
                        }
                        if (failurePermissions.size == 0 && askNeverAgainPermissions.size == 0) {
                            LogUtils.debugLongInfo("Request permissions success")
                            requestPermission.onRequestPermissionSuccess()
                        }
                    }, {
                        it.printStackTrace()
                    })


        }


    }

    /**
     * 请求摄像头权限
     */
    fun launchCamera(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
    }

    /**
     * 请求文件读写权限
     */
    fun launchExternal(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
    }

    /**
     * 请求外部存储的权限
     */
    fun externalStorage(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    /**
     * 请求发送短信权限
     */
    fun sendSms(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.SEND_SMS)
    }

    /**
     * 请求打电话权限
     */
    fun callPhone(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.CALL_PHONE)
    }

    /**
     * 请求定位权限
     */
    fun callLocation(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
    }

    /**
     * 请求获取手机状态的权限
     */
    fun readPhoneState(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.READ_PHONE_STATE)
    }

    /**
     * 请求连接蓝牙权限
     */
    fun launchBluetooth(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.BLUETOOTH_ADMIN)
    }

    /**
     * 请求日历权限
     */
    fun launchCalendar(requestPermission: RequestPermission, rxPermissions: RxPermissions) {
        requestPermission(requestPermission, rxPermissions, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR)
    }
}