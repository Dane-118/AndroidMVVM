package com.example.android.study.commonsdk.utils

import java.math.BigDecimal
import java.text.DecimalFormat

/**
 * @ClassName: CalcUtil
 * @Description: 描述
 * @Author: Shao Yang
 * @CreateDate: 2019/12/25 9:58
 * @Version: 1.0
 */
object CalcUtil {
    /**
     * TODO 除法运算，保留小数
     * @author 袁忠明
     * @date 2018-4-17下午2:24:48
     * @param a 被除数
     * @param b 除数
     * @return 商
     */
    fun txfloat(a: Int, b: Int): String {
        // TODO 自动生成的方法存根
        val df = DecimalFormat("0.00") //设置保留位数
        return df.format(a.toFloat() / b.toDouble())
    }

    /**
     * @desc 1.0~1之间的BigDecimal小数，格式化后失去前面的0,则前面直接加上0。
     * 2.传入的参数等于0，则直接返回字符串"0.00"
     * 3.大于1的小数，直接格式化返回字符串
     * @param obj 传入的小数
     * @return
     */
    fun formatToNumber(obj: BigDecimal?): String {
        if (obj == null) {
            return "0.00"
        }
        val df = DecimalFormat("#.00")
        return if (obj.compareTo(BigDecimal.ZERO) == 0) {
            "0.00"
        } else if (obj > BigDecimal.ZERO && obj < BigDecimal(1)) {
            "0" + df.format(obj).toString()
        } else {
            df.format(obj).toString()
        }
    }

    fun formatPrice(price: Double): String {
        val df = DecimalFormat("#0.00")
        return df.format(price)
    }



    /**
     * 校验车次号是否合法
     */
    fun checkTrainCode(code: String) = code.matches(Regex("^[CGDKPSTVYZ]?[0-9]{1,5}\$"))

}