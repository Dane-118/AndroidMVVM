package com.example.android.study.commonres.ext

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import cn.jpush.android.api.JPushInterface
import com.blankj.utilcode.util.ToastUtils
import com.example.android.study.commonres.app.KeyUtils
import com.example.android.study.commonres.app.RouterHub
import com.example.android.study.push.core.annotation.CommandType
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.hgj.jetpackmvvm.base.appContext
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.util.logd
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.net.URI
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit

/** @Date：1/27/21 高旅账号是否登录 */
fun isLogin(): Boolean = mmkv.decodeString(KeyUtils.KEY_TOKEN, "").isNotEmpty()
        && mmkv.decodeString(KeyUtils.MEMBER_ID, "").isNotEmpty()

/** @Date：1/27/21 12306账号是否登录 */
fun isLogin12306(): Boolean = mmkv.decodeBool(KeyUtils.IS_LOGIN_12306, false)


fun showMessage(message: String) {
    if (message.isNotEmpty()) {
        ToastUtils.showShort(message!!)
    }
}


fun Bundle?.launcherAndFinish(activity: AppCompatActivity) {
    val path = this?.getString(RouterHub.PATH)
    if (!TextUtils.isEmpty(path)) {
        path?.navigation(activity, bundle = this, onArrival = {
            activity.finish()
        }, onInterrupt = {
            activity.finish()
        })
    } else {
        activity.finish()
    }


}


fun BaseViewModel.countDown(number: Long, live: MutableLiveData<Long>, timeUnit: TimeUnit = TimeUnit.SECONDS): Job {
    return viewModelScope.launch {
        Thread.currentThread().name.logd("wp")
        val count = when (timeUnit) {
            TimeUnit.SECONDS -> 1000L
            TimeUnit.MINUTES -> 1000 * 60.toLong()
            TimeUnit.HOURS -> 1000 * 60 * 60.toLong()
            else ->
                1000L
        }
        if (number <= 0L) {
            live.postValue(number)
        } else {
            for (i in number downTo 1) {
                delay(count)
                live.postValue(i)
            }
        }
    }
}

fun BaseViewModel.countDown(number: Long, timeUnit: TimeUnit = TimeUnit.SECONDS, action: (Long) -> Unit): Job {
    return viewModelScope.launch {
        Thread.currentThread().name.logd("wp")
        val count = when (timeUnit) {
            TimeUnit.SECONDS -> 1000L
            TimeUnit.MINUTES -> 1000 * 60.toLong()
            TimeUnit.HOURS -> 1000 * 60 * 60.toLong()
            else ->
                1000L
        }
        if (number <= 0L) {
            action.invoke(number)
        } else {
            for (i in number downTo 1) {
                delay(count)
                action.invoke(i)
            }
        }
    }
}

/**
 * 拦截登录操作，如果没有登录执行方法 actionLogin 登录过了执行 action
 */
fun jumpByLogin(
        action: () -> Unit,
        actionLogin: () -> Unit

) {
    if (isLogin()) {
        action()
    } else {
        actionLogin()
    }
}

/**
 * 获取进程号对应的进程名
 *
 * @param pid 进程号
 * @return 进程名
 */
fun getProcessName(pid: Int): String? {
    var reader: BufferedReader? = null
    try {
        reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
        var processName = reader.readLine()
        if (!TextUtils.isEmpty(processName)) {
            processName = processName.trim { it <= ' ' }
        }
        return processName
    } catch (throwable: Throwable) {
        throwable.printStackTrace()
    } finally {
        try {
            reader?.close()
        } catch (exception: IOException) {
            exception.printStackTrace()
        }

    }
    return null
}


fun List<*>?.isNull(): Boolean {
    return this?.isEmpty() ?: true
}

fun List<*>?.isNotNull(): Boolean {
    return this != null && this.isNotEmpty()
}

/**
 * 根据索引获取集合的child值
 * @receiver List<T>?
 * @param position Int
 * @return T?
 */
inline fun <reified T> List<T>?.getChild(position: Int): T? {
    //如果List为null 返回null
    return if (this == null) {
        null
    } else {
        //如果position大于集合的size 返回null
        if (position + 1 > this.size) {
            null
        } else {
            //返回正常数据
            this[position]
        }
    }
}


fun String?.checkUrl(baseImageUrl: String): String {
    if (TextUtils.isEmpty(this)) {
        return ""
    }
    val uri: URI?
    try {
        uri = URI(this!!)
    } catch (e: URISyntaxException) {
        e.printStackTrace()
        return baseImageUrl + this
    }
    if (uri.host == null) {
        return baseImageUrl + this
    }
    return if (uri.scheme.equals("http", true) || uri.scheme.equals("https", true)) {
        this
    } else {
        baseImageUrl + this
    }

}




fun delCache() {
    try {
        JPushInterface.deleteAlias(appContext, CommandType.TYPE_BIND_ALIAS)
        JPushInterface.deleteTags(appContext, 1, setOf("consumer"))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    mmkv.removeValueForKey(KeyUtils.KEY_TOKEN)
    mmkv.removeValueForKey(KeyUtils.PHONE_NUM)
    mmkv.removeValueForKey(KeyUtils.MEMBER_ID)
    mmkv.removeValueForKey(KeyUtils.USER_INFO)
    mmkv.encode(KeyUtils.KEY_BAGGAGE_STATUS, false)
}
