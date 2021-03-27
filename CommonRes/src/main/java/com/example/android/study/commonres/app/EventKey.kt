package com.example.android.study.commonres.app

/**
 * 描述：
 *
 * @author {Wang Peng} by 12/25/20
 */
object EventKey {

    const val REFRESH_ADDRESS_LIST: String = "del_address_success"
    const val EVENT_ADDRESS_SELECTED_RESPONSE: String = "event_address_selected_response"
    const val EVENT_ADDRESS_LIST_RESPONSE: String = "event_address_list_response"
    const val EVENT_TO_LOGIN = "event_to_login"

    const val EVENT_PASSENGER_LIST = "event_passenger_list"
    const val EVENT_BLE_STOP_ALARM = "event_ble_stop_alarm"

    const val EVENT_SELECTED_SERVICE = "event_selected_service"


    /**2/26/21 支付成功 */
    const val EVENT_PAY_RESULT = "event_pay_success"

    const val EVENT_ROB_TRAIN_LIST_OPT = "event_rob_train_list_opt"

    const val EVENT_CAN_USE_BACK = "event_can_use_back"


    //分享
    const val EVENT_SHARE_SUCCESS: String = "event_share_success"
    const val EVENT_SHARE_ERROR: String = "event_share_error"
    const val EVENT_SHARE_CANCEL: String = "event_share_cancel"
    const val EVENT_CHECK_PHONE_LIST: String = "event_check_phone_list"

}