package com.example.android.study.commonres.app


/**
 * 描述：本地存储 KEY
 *
 * @author {Wang Peng} by 2020/10/26
 */
object KeyUtils {

    /**城市选择器 */

    const val KEY_FIRST_LOCATION = "key_first_location"
    const val KEY_LOCATION: String = "key_location"
    const val KEY_CITY_CHANGE: String = "key_city_change"
    const val KEY_REAL_CITY: String = "key_real_city"
    const val LOCAL_REAL_CITY = "local_real_city"

    /** @Date：1/26/21 拦截器标识 */
    const val INTERCEPTOR_LOGIN = "RouterLoginInterceptor"


    /** @Date：1/26/21 渠道 id */
    const val CHANNEL_GAO_LV = "0"
    const val CHANNEL_12306 = "1"


    const val KEY_AD_IS_SHOW: String = "key_ad_is_show"
    const val KEY_IS_AROUTER: String = "key_is_arouter"
    const val KEY_TITTLE = "key_tittle"
    const val KEY_CONTENT = "key_content"


    //第一次进入app
    const val FIRST_OPEN = "first_open"

    //出发城市
    const val KEY_IS_CHANGE_CITY: String = "key_is_change_city"
    const val KEY_FORM_DATE: String = "key_form_date"
    const val KEY_TICKET_DATE_DESC: String = "key_ticket_date_desc"
    const val KEY_TO_CITY: String = "key_to_city"
    const val KEY_FORM_CITY: String = "key_form_city"

    const val KEY_TOKEN = "key_token"
    const val IS_LOGIN_12306 = "IS_LOGIN_12306"
    const val TICKET_PHONE_NUM: String = "ticket_phone_num"
    const val MEMBER_ID: String = "member_id"
    const val USER_INFO: String = "user_info"
    const val KEY_BLE_HISTORY_LIST = "key_ble_history_list"
    const val KEY_BAGGAGE_STATUS: String = "key_baggage_status"
    const val PHONE_NUM = "phone_num"

    const val KEY_USER_NAME: String = "key_user_name"
    const val AGREEMENT = "user_agreement"
    const val KEY_PASSENGER_LIST: String = "key_passenger_list"

    //新手引导
    const val NEW_SPLASH = "new_splash"

    const val KEY_CHANGE_TICKET_REQUEST: String = "key_order_passenger_id"
    const val KEY_IS_UPDATE: String = "key_is_update"

    const val TARGET_DAY = "target_day"


    /**
     * 车站大屏
     */
    const val STATION = "station"

//    val DEFAULT_CITY = City("济南", "JNK", "jna", "jinan", "JNK", "jn", "jinan")

    const val NONE_TEL_CODE = "-100"

//    val NONE_CITY = City("定位刷新", NONE_TEL_CODE)

    const val LOCATION_CITY = "location_city"

    const val STATION_LIST = "station_info"

    const val CAR = "car_info"
    const val STATION_ALL = "station_all"
    const val REAL_CITY_LIST = "real_city_info"

    const val DEVICE_INFO: String = "device_info"
    const val LAST_OPEN_TIME: String = "last_open_time"

}