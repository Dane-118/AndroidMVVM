package com.example.android.study.commonres.utils

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.InputFilter
import android.widget.EditText
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.ResourceUtils
import com.blankj.utilcode.util.SizeUtils
import com.example.android.study.commonres.utils.filters.DotTwoFilter
import com.example.android.study.commonres.utils.filters.EditAssociateFilter
import com.example.android.study.commonres.utils.filters.SpaceFilter
import com.example.android.study.commonres.utils.filters.TrainNumberFilter
import java.math.BigDecimal


/**
 * 描述：配置扩展类
 *
 * @author JiaoPeng by 2020/7/17
 */

/**
 * 设置输入框不可输入空格和换行
 */
fun EditText.setSpaceFilter() {
    this.filters = arrayOf<InputFilter>(SpaceFilter())
}

/**
 * 设置输入框最大输入数量，xml中设置无效是因为和上面的filter冲突
 * @param length 目标最大长度
 */
fun EditText.setLengthFilter(length: Int) {
    this.filters = arrayOf(SpaceFilter(), InputFilter.LengthFilter(length), EditAssociateFilter())
}

/**
 * 设置输入框只能输入大写字母和数字，并且输入的小写字母会自动转换为大写字母
 */
fun EditText.setTrainNumberFilter() {
    this.filters = arrayOf(
            InputFilter.AllCaps(),
            TrainNumberFilter(),
            EditAssociateFilter()
    )
}

/**
 * 设置输入框最大输入数量，xml中设置无效是因为和上面的filter冲突
 * 设置输入框只能输入大写字母和数字，并且输入的小写字母会自动转换为大写字母
 * 设置输入框不可输入空格和换行
 * @param length 目标最大长度
 */
fun EditText.setTrainNumberLengthSpaceFilter(length: Int) {
    this.filters = arrayOf(
            SpaceFilter(),
            InputFilter.LengthFilter(length),
            InputFilter.AllCaps(),
            TrainNumberFilter())
}

/**
 * 设置输入框最大输入数量，xml中设置无效是因为和上面的filter冲突
 * 设置输入框不可输入空格和换行
 * @param length 目标最大长度
 */
fun EditText.setLengthSpaceFilter(length: Int) {
    this.filters = arrayOf(
            SpaceFilter(),
            InputFilter.LengthFilter(length))
}

/**
 * 设置输入框不可输入空格和换行
 * 设置输入框只能输入大写字母和数字，并且输入的小写字母会自动转换为大写字母
 */
fun EditText.setTrainNumberSpace() {
    this.filters = arrayOf(
            SpaceFilter(),
            InputFilter.AllCaps(),
            TrainNumberFilter())
}

/**
 * 清除掉字符串中掉所有的空格，包括中间和前后
 */
fun String.trimSpace(): String {
    return this.replace(" ", "")
}

/**
 * 设置Button的左侧图标,不包含文字
 */
fun TextView.setStartDrawableImage(
        res: Int,
        bounds: Float = 16f,
        padding: Float = 20f,
        clearPadding: Boolean = false,

        ) {
    this.text = ""
    val drawable = ResourceUtils.getDrawable(res)
    drawable.setBounds(0, 0, SizeUtils.dp2px(bounds), SizeUtils.dp2px(bounds))
    if (clearPadding) {
        this.setPadding(SizeUtils.dp2px(padding), 0, 0, 0)
    }
    this.setCompoundDrawables(drawable, null, null, null)
}

/**
 * 设置Button的左侧图标,包含文字
 */
fun TextView.setStartDrawableImageAndText(res: Int,
                                          text: String,
                                          bounds: Float = 16f,
                                          drawablePadding: Float = 8F) {
    this.text = text
    val drawable = ResourceUtils.getDrawable(res)
    drawable.setBounds(0, 0, SizeUtils.dp2px(bounds), SizeUtils.dp2px(bounds))
    this.setCompoundDrawables(drawable, null, null, null)
    this.compoundDrawablePadding = SizeUtils.dp2px(drawablePadding)
}

/**
 * 设置Button的左侧图标,包含文字
 */
fun TextView.setStartDrawableImageAndText(drawable: Drawable,
                                          text: String,
                                          bounds: Float = 16f,
                                          drawablePadding: Float = 8F) {
    this.text = text
    drawable.setBounds(0, 0, SizeUtils.dp2px(bounds), SizeUtils.dp2px(bounds))
    this.setCompoundDrawables(drawable, null, null, null)
    this.compoundDrawablePadding = SizeUtils.dp2px(drawablePadding)
}

/**
 * 设置输入框输入小数点后两位
 */
fun EditText.setDotFilter() {
    this.filters = arrayOf<InputFilter>(DotTwoFilter())
}

/**
 * 设置Button的右侧图标,不包含文字
 */
fun TextView.setEndDrawableImage(
        res: Int,
        bounds: Float = 16f,
        padding: Float = 20f,
        clearPadding: Boolean = false,
) {
    this.text = ""
    val drawable = ResourceUtils.getDrawable(res)
    drawable.setBounds(0, 0, SizeUtils.dp2px(bounds), SizeUtils.dp2px(bounds))
    if (clearPadding) {
        this.setPadding(0, 0, SizeUtils.dp2px(padding), 0)
    }
    this.setCompoundDrawables(null, null, drawable, null)
}

/**
 * 设置Button的右侧图标,包含文字
 */
fun TextView.setEndDrawableImageAndText(res: Int, text: String,
                                        color: Int? = null,
                                        bounds: Float = 16f,
                                        drawablePadding: Float = 8f) {
    this.text = text
    color?.let {
        this.setTextColor(ColorUtils.getColor(color))
    }
    val drawable = ResourceUtils.getDrawable(res)
    drawable.setBounds(0, 0, SizeUtils.dp2px(bounds), SizeUtils.dp2px(bounds))
    this.setCompoundDrawables(null, null, drawable, null)
    this.compoundDrawablePadding = SizeUtils.dp2px(drawablePadding)
}

/**
 * 设置Button的右侧文字
 */
fun TextView.setEndText(text: String, color: Int, padding: Float = 20f) {
    this.minHeight = 0
    this.minWidth = 0
    this.minimumHeight = 0
    this.minimumWidth = 0
    this.text = text
    this.setTextColor(ColorUtils.getColor(color))
    this.setCompoundDrawables(null, null, null, null)
    this.setPadding(0, 0, SizeUtils.dp2px(padding), 0)
}


/**
 * 显示BigDecimal去除0之后的字符串
 */
fun BigDecimal?.showNoZeroString(): String {
    val result = (this ?: BigDecimal.ZERO).stripTrailingZeros().toPlainString()
    return if (result == "") "0" else result
}


/**
 * 秒转hh:mm:ss格式
 * @param secString 秒字符串
 * @return hh小时mm分ss秒
 * String
 */
fun secondsToFormat(secString: String): String? {
    val seconds = secString.toLong()
    var day = 0L
    var hour = 0L
    var min = 0L
    var second = 0L
    var result = ""
    second = seconds % 60 //秒
    if (seconds > 60) {   //是否大于零
        min = seconds / 60 //分钟
        if (min > 60) {   //存在时
            hour = min / 60
            if (hour > 24) {
                day = hour / 24
                hour %= 24
            }
            min %= 60
        }
    } else if (seconds == 60L) {
        second = 60
    }

    if (day > 0) {
        result = day.toString() + "天"
    }

    if (hour > 0) {
        result = result + hour.toString() + "时"
    }
    if (min > 0) {
        result = result + min + "分"
    } else if (min == 0L && hour > 0) {  //当分为0时,但是时有值,所以要显示,避免出现2时0秒现象
        result = result + min + "分"
    }
    result = result + second + "秒" //秒必须出现无论是否大于零
    return result
}


/**
 * 判断是否安装微信
 *
 * @param context
 * @return
 */
fun isWxInstall(context: Context): Boolean {
    // 获取packageManager
    val packageManager: PackageManager = context.packageManager
    // 获取所有已安装程序的包信息
    val pInfo = packageManager.getInstalledPackages(0)
    if (pInfo != null) {
        for (i in pInfo.indices) {
            val pn = pInfo[i].packageName
            if (pn == "com.tencent.mm") {
                return true
            }
        }
    }
    return false
}

/**
 * 检测是否安装支付宝
 *
 * @param context
 * @return
 */
fun isAliPayInstall(context: Context): Boolean {
    val uri = Uri.parse("alipays://platformapi/startApp")
    val intent = Intent(Intent.ACTION_VIEW, uri)
    val componentName = intent.resolveActivity(context.packageManager)
    return componentName != null
}


/**
 * 获取渠道 Id
 */

fun getChannelId(context: Context, type: Boolean = false): String {
    var channel = ""
    val info: ApplicationInfo
    try {
        info = context.applicationContext.packageManager
                .getApplicationInfo(context.applicationContext.packageName, PackageManager.GET_META_DATA)
        channel = info.metaData.getString("channel_type").toString()

    } catch (e: Exception) {
        e.printStackTrace()
    }
    if (type) return channel
    return whenChannel(channel)
}

fun whenChannel(channel: String): String = when {
    channel.contains("小米") -> "001"
    channel.contains("华为") -> "002"
    channel.contains("VIVO", true) -> "003"
    channel.contains("OPPO", true) -> "004"
    channel.contains("魅族") -> "005"
    channel.contains("应用宝") -> "006"
    channel.contains("百度") -> "007"
    channel.contains("360") -> "008"
    else -> "010"
}
