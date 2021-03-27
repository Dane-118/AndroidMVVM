package com.example.android.study.commonres.utils


class StringUtil {
    companion object {

        /**
         * 用星号代替部分内容
         * @param info 原字符串
         * @param startId 开始替换的位置
         * @param endId 结束替换的位置
         * @param use 使用替换的符号，默认是 *
         * @return 替换之后的结果
         */
        fun replaceWithStar(info: String, startId: Int = 3, endId: Int = 7, use: String = "****"): String {
            val replace: String = info
            return if (replace.length < startId || replace.length < endId) {
                replace
            } else {
                StringBuilder(replace).replace(startId, endId, use).toString()
            }
        }

        /**
         * 判断是否为有效的网站格式
         *
         * @param uri
         * @return
         */
        fun isUri(uri: String): Boolean {
            return uri.indexOf("http") != -1 || uri.indexOf("https") != -1
        }

        /**
         * 整理检票口/候车室的显示规则
         */
        fun adjustString(info: String): String {
            return if (info != "--/--") {
                val value = when {
                    info.startsWith("--/") -> {
                        info.replace("--/", "")
                    }
                    info.endsWith("/--") -> {
                        info.replace("/--", "")
                    }
                    else -> {
                        info
                    }
                }
                value
            } else {
                info
            }
        }

        /**
         * 超过7个省略
         */
        fun sub7String(text: String): String {
            var str = text
            if (str.length > 7) {
                str = str.substring(0, 7) + "..."
            }
            return str

        }

        /**
         * 超过3个省略
         */
        fun sub3String(text: String): String {
            var str = text
            if (str.length > 3) {
                str = str.substring(0, 3) + "..."
            }
            return str

        }

    }
}