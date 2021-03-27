package com.example.android.study.commonres.network



object AppConstant {

    /**2/19/21 添加乘客 */
    const val PASSENGER_ADD: Int = 1
    const val PASSENGER_UPDATE: Int = 2
    const val PASSENGER_ADD_CHILD: Int = 3

    const val OPPO: String = "004"
    const val GAO_LV_DB_NAME: String = "gaolv.db"
    const val IMG_STATIC_URL = "https://static.gaolvzongheng.com"

    /**
     * 请求头
     */
    const val User_Agent = "Android"
    const val CLIENT_ID = "100010"

    /**
     * 登录 header
     */
    const val APP_QUICK_LOGIN = "APP_QUICK_LOGIN"
    const val APP_LOGIN = "APP_SMS_CODE"


    /**
     * 缓存文件夹名称
     */
    const val LOCAL_FILE_NAME = "/gaolv/"
    const val CHANNEL_OPPO = "OPPO"

    //首页开屏弹窗广告是否显示
    var dialogAdShow = true

    const val PLACE_HOLDER = "-"

    /**
     * 热修复
     */
    const val APP_KEY = "32141474"
    const val APP_SECRET = "56473b443d0bc539fab137fa45b27d3f"
    const val RSA_SECRET = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCgDt8qrtPPIeEz40CDuXKsLkjNcdsnrk+wc7n9pX9NROwEa2sco6ktQitlNgvSEHD/epuyWcs6ec9NNqJ3ILs1LpZ3hRE1AKXpMNNQ1t5E75OMoxXvRqdGnsbqny/BrJklI55epmThOq/yZdxYQGbEBZmcqDE3BaaxKVOPLpKaWraVwH53F+tJImif04ve8cN+lu2Uj4DPpZ1mv/JLjv/595M/OjXWWLqfxM4SM5RAon+qFOdA5NHJe9vcbFxW+zG402spKC+Bm0X/RR8VmsmWqAgiebIt8img/BmSVfzVAZak5YQRpFGMU+L5n1sEkGrtE4gJfJyf5Id4RO6xtxe5AgMBAAECggEAen0nGFkWy0tYNwdQ1f3T3iTFnkPlvaZOYfEOgyhmFdIK9Q7nt9EfpP0gjKIhxa7DayB/iqvhNW0G0kZfat9npTlz+R+inXdTgPPWo+Tr0MGvn08neCgP16GnBOKx/3uA78Uka7bXOO5avBxr1jXi71yBxDuK/lqayLWhts+jbe+GWKt2QySn/7oHtfKtEXFR81k8P3GqG/YdQCEiODNs3XCJoAdwSU3j2WJM8wTLVrxWik54Sohv6gyQGzCtgCAUjc3v/3qyz/21vHLw/UeXcU7TB9GLmVIpDxJANEiNvgGM6ZJIINVBWH/la21OnyqI64gGLWtU/MxkHYIi5ErNIQKBgQD17heoKLFvNqTPAiBzczR4MwTMXSuyxw1JVinRrFLReVOQuUyU+3fs1NvrclDWfzNJ0Pky4xqk5xyKTeIqVBdNwirmvNTP1hyn0InMMsdMdfZ+mbtkETbZKHKf0IXcxRNvL1yCUkzmV4eWpoa5nA4yU+W5wLZPfyrthh3QM6ScfQKBgQCmnKU9LL+MagdjSUM8K63Cgd/RpbwPYWXsK8pP0c9iNFWxz7hwrVECrTjzA0RvcB1coImWH52sh5Mymw/NKsRXstHkwQr+WSO2S1pi3H5taYKBM2BhEKqZ2pf+8gYt7swDFI7Y/9egjBXHneabuthR0sfvo3MUPCDBEMwwf4iY7QKBgAaIfa5k7lnC/NvW9Z+7hsBOsqjvLLMCILM7C79bMZQJAhRNJ+CpUYVEHleB4O3h0S371DLq35FT5mkyKnjvZPMW3mbky05+JKK8onx/ONYqKBMy6Ex/PJLGRU1Dj9rOjXadrnpzz0ErQBnbcKbLo4Dow6iMmws/mJdYwb//LzdNAoGAIttc2pqVDHxWRVhbf6gntyzStZq16IXY+/sBXYeomoQWdZfen3wzGZtB8d16INJU4j8X6dd9dk365rh4KtVKtIpNzUzxztq5VPcY1twIez16tysXe9cvyfZonEboMg3mkrJnXFDSPN1x5QxM5cHGZf/yIlmQxuf5dkrcXT0hR9UCgYEAiGyccfo8QLpP7lj8eFs/0apzhkz0639jkbtKzIWnz326qhOIckh2ZlqbdHqnYJ2GKkbKfaMqiimsOQKUvZSuocahYMGxxrdjbvnKWU6GSx89gbnBFopseFqFl53jZTwP1oJ7UKyuH2vC7P2j9MFX18fI0Mzy1rj02/7yIxBNZts="


    const val _0_5 = "0.5"
    const val _0_75 = "0.75"


    const val CHANNEL_GAO_LV = "0"
    const val CHANNEL_12306 = "1"

    const val YEAR_MONTH_DAY = "yyyyMMdd"

    const val YEAR_MONTH_DAY_LINE = "yyyy-MM-dd"


    const val TYPE_HAND: String = "手动选择"
    const val TYPE_AUTO: String = "自动定位"
    const val A_WEEK_TIME = 518400000



    /**
     * 数美风控
     */
    const val SM_ORGANIZATION = "8E9h1sW6OluQR0VMZf8b"
    const val SM_APPID = "default"
    const val SM_PUBLICKEY = "MIIDLzCCAhegAwIBAgIBMDANBgkqhkiG9w0BAQUFADAyMQswCQYDVQQGEwJDTjELMAkGA1UECwwCU00xFjAUBgNVBAMMDWUuaXNodW1laS5jb20wHhcNMjAxMDE5MDIzMDEyWhcNNDAxMDE0MDIzMDEyWjAyMQswCQYDVQQGEwJDTjELMAkGA1UECwwCU00xFjAUBgNVBAMMDWUuaXNodW1laS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCXSStsvOiTksu7ARvc5IAuV3E/jkCSgcmKybLLT/yEGN0CEuGspp5wiK9yQdn7xMy6E/t4DleMA7DMzITh0i8+bCJ8CMpvzB5lszlztciA2w7YVj/+JNpYaykmlSsZqfbx8Qm8fpYsvJHeldX7w7v0vGh/OuxbGoNNTNvIMmy7nWSaoYyd6m/c8Qw5J4Ow0+XUSvSSC/HBVlVd8RpR7YNtm9/qU1wrI/00rkq+m99Fx0toA4KmaNiGJTDMUhC9PF5m8G2X8ZxJ9HBN74WeCOrTZGDal+b/wUfLGE2F8dJiutJfeuUyZFBvKCvH5rKBbVi6xTz9RNx0zS9dJcU4n3wZAgMBAAGjUDBOMB0GA1UdDgQWBBRn4lwhRL+nJzn+DL0kU8YtV4fEeTAfBgNVHSMEGDAWgBRn4lwhRL+nJzn+DL0kU8YtV4fEeTAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBQUAA4IBAQB2dL9ADmHtK6WgnPThBB2G2N69loe7VZqFNB/0tmTdLNrotMcut8yblspZrlqrzsVGYObmpU9AfUWfzpOBBjVd/RfIR3qgDRnPfw6M/eAfDYojDbqO33qBHzYFcq278GyCbSvz99kzcXAdVAMcVSMAVoIjhnOC4jb7TWlyF3oKmC/bzm3cgfZf01yHvXx4a4eutFQqO2cFje8aKRCvfBaPggPWS8ITf8q+qTEj2nwiZ3CT5oydLahqbFSQwcYOe0Pb5f3wKQSKg2Bvs5lof/3DLX/10Nau23uJ3Gut+mtVRSP6XcQEmVwI6795TRBGBGOzBLRmiXkSoK4ylpfSoY4G"
    const val SM_AINFOKEY = "XtjbEDkPGjKVnwARSnOcQLEAOtamSlzoAHHPPueVkBswgKmQOvlXTaoprjeIznXB"



    const val A_WEEK_DAY = 7
}