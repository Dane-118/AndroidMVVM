/*
 * Copyright 2018 JessYan
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
package com.example.android.study.commonres.app

/**
 * ================================================
 * RouterHub 用来定义路由器的路由地址, 以组件名作为前缀, 对每个组件的路由地址进行分组, 可以统一查看和管理所有分组的路由地址
 *
 *
 * RouterHub 存在于基础库, 可以被看作是所有组件都需要遵守的通讯协议, 里面不仅可以放路由地址常量, 还可以放跨组件传递数据时命名的各种 Key 值
 * 再配以适当注释, 任何组件开发人员不需要事先沟通只要依赖了这个协议, 就知道了各自该怎样协同工作, 既提高了效率又降低了出错风险, 约定的东西自然要比口头上说强
 *
 *
 * 如果您觉得把每个路由地址都写在基础库的 RouterHub 中, 太麻烦了, 也可以在每个组件内部建立一个私有 RouterHub, 将不需要跨组件的
 * 路由地址放入私有 RouterHub 中管理, 只将需要跨组件的路由地址放入基础库的公有 RouterHub 中管理, 如果您不需要集中管理所有路由地址的话
 * 这也是比较推荐的一种方式
 *
 *
 * 路由地址的命名规则为 组件名 + 页面名, 如订单组件的订单详情页的路由地址可以命名为 "/order/OrderDetailActivity"
 *
 *
 * ARouter 将路由地址中第一个 '/' 后面的字符称为 Group, 比如上面的示例路由地址中 order 就是 Group, 以 order 开头的地址都被分配该 Group 下
 * 切记不同的组件中不能出现名称一样的 Group, 否则会发生该 Group 下的部分路由地址找不到的情况!!!
 * 所以每个组件使用自己的组件名作为 Group 是比较好的选择, 毕竟组件不会重名
 *
 * @see [RouterHub wiki 官方文档](https://github.com/JessYanCoding/ArmsComponent/wiki.3.4)
 * Created by JessYan on 30/03/2018 18:07
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
object RouterHub {


    /**
     * 组名
     */
    //    String APP = "/app";//宿主 App 组件
    const val HOME = "/home"
    const val APP = "/APP"
    const val LOGIN = "/login"
    const val LOGIN12306 = "/login12306"
    const val LOST = "/lost"
    const val MINE = "/mine"
    const val TICKET = "/ticket"
    const val TRAVEL = "/travel"
    const val BLUETOOTH = "/bluetooth"
    const val ROB = "/rob"
    const val LAUNCHER = "/launcher"
    const val WEB = "/web"
    const val PROTOCOL = "/protocol"
    const val ADVERT = "/advert"
    const val TIME = "/time"
    const val SCREEN = "/SCREEN"
    const val CUSTOMER = "/customer"
    const val SCAN = "/scan"
    const val GOOD = "/good"
    const val MTICKET = "/mticket"
    const val MLOST = "/mlost"
    const val ADDRESS = "/address"
    const val COUPON = "/coupon"
    const val WALLET = "/wallet"
    const val HELP = "/help"
    const val INFO = "/info"
    const val MESSAGE = "/message"
    const val SETTING = "/setting"
    const val PAY = "/pay"
    const val CARD = "/card"
    const val PASSENGER = "/passenger"
    const val TICKET_ORDER = "/ticket_order"


    const val PATH = "path"

    /**
     * 服务组件, 用于给每个组件暴露特有的服务
     */
    const val SERVICE = "/service"


    /**
     * 宿主 App 分组
     */
    const val APP_LAUNCHER_ACTIVITY = "$APP/LauncherActivity"
    const val APP_FORCED_UPGRADE_ACTIVITY = "$APP/ForcedUpgradeActivity"
    const val APP_UPGRADE_ACTIVITY = "$APP/UpgradeActivity"
    const val APP_BASE_SERVICE = "$APP$SERVICE/IBaseServiceImpl"
    const val LOGIN_OUT = "login_out"

    /**
     * 广告分组
     */
    const val ADVERT_AD = "advert_ad"

    const val ADVERT_SERVICE = "$ADVERT$SERVICE/IAdvertServiceImpl"
    const val ADVERT_ACTIVITY = "$ADVERT/AdvertActivity"
    const val IBANNER_SERVICE = "$ADVERT$SERVICE/IBannerServiceImpl"
    const val ADVERT_GUIDE_ACTIVITY = "$ADVERT/GuideActivity"

    /**
     * 支付分组
     */
    const val PAY_SELECT_ACTIVITY = "$PAY/PaySelectedActivity"
    const val PAY_KEY_PAY_BEAN = "pay_key_pay_bean"


    const val PAY_RESULT_ACTIVITY = "$PAY/PayResultActivity"
    const val PAY_TO_PAY_RESULT_PARAMS = "pay_to_pay_result_params"
    const val PAY_ORDER_ID = "pay_order_id"


    /**
     * 蓝牙分组
     */
    const val BLUETOOTH_SERVICE = "$BLUETOOTH$SERVICE/IAdvertServiceImpl"
    const val BLUETOOTH_DEVICE_ACTIVITY = "$BLUETOOTH/BluetoothDeviceActivity"

    /**
     * home分组
     */
    const val HOME_ACTIVITY_IS_FIRST = "home_activity_is_first"
    const val HOME_ACTIVITY = "$HOME/HomeActivity"
    const val HOME_CITY_CHOICE_ACTIVITY = "$HOME/CityChoiceActivity"

    const val HOME_SERVICE = "$HOME$SERVICE/HomeServiceImpl"
    const val HOME_TICKET_REQUEST = "home_ticket_request"

    const val HOME_PAGE_FRAGMENT = "$HOME/HomePageFragment"

    /**
     * lost分组
     */
    const val LOST_SERVICE = "$LOST$SERVICE/LostServiceImpl"
    const val LOST_ACTIVITY = "$LOST/LostFindActivity"

    /**
     * 我的失物分组
     */
    const val MLOST_SERVICE = "$MLOST$SERVICE/LostServiceImpl"
    const val MLOST_ACTIVITY = "$MLOST/MyLostActivity"

    /**
     * 行李卡
     */
    const val CARD_LUGGAGE_ACTIVITY = "$CARD/LuggageActivity"
    const val CARD_BLE_SEARCH_ACTIVITY = "$CARD/BleSearchMainActivity"
    const val CARD_BLE_PHONE_ALARM_ACTIVITY = "$CARD/BlePhoneAlarmActivity"
    const val KEY_CARD_FRAGMENT_TYPE_BUNDLE = "key_card_fragment_type_bundle"
    const val CARD_SERVICE = "$CARD$SERVICE/ICardServiceImpl"


    /**
     * 我的商品
     */
    const val GOOD_SERVICE = "$GOOD$SERVICE/LostServiceImpl"
    const val GOOD_ACTIVITY = "$GOOD/GoodActivity"
    const val GOOD_ORDER_DETAILS_ACTIVITY = "$GOOD/GoodOrderDetailsActivity"
    const val GOOD_REFUND_DETAILS_ACTIVITY = "$GOOD/GoodRefundDetailsActivity"
    const val GOOD_LOGISTICS_ACTIVITY = "$GOOD/GoodLogisticsActivity"
    const val GOOD_COMMENT_ACTIVITY = "$GOOD/GoodCommentActivity"
    const val GOOD_COMMENT_DETAILS_ACTIVITY = "$GOOD/CommentDetailsActivity"
    const val GOOD_POST_COMMENTS_ACTIVITY = "$GOOD/PostCommentsActivity"
    const val GOOD_INVOICE_ACTIVITY = "$GOOD/GoodInvoiceActivity"
    const val WRITE_LOGISTICS_ACTIVITY = "$GOOD/WriteLogisticsActivity"
    const val REQUEST_REFUND_ACTIVITY = "$GOOD/RequestRefundActivity"
    const val ORDER_ID = "order_Id"
    const val GOODS = "goods"
    const val RETURN_CODE = "return_code"
    const val GOOD_IMAGE = "good_image"
    const val GOOD_PRICE = "good_price"
    const val REFUND_TYPE = "refund_type"

    /**
     * 我的车票
     */
    const val MTICKET_SERVICE = "$MTICKET$SERVICE/LostServiceImpl"
    const val MTICKET_ACTIVITY = "$MTICKET/AllTicketActivity"

    /**
     * 地址管理
     */
    const val ADDRESS_SERVICE = "$ADDRESS$SERVICE/IAddressServiceImpl"
    const val ADDRESS_MANAGER_ACTIVITY = "$ADDRESS/AddressManagerActivity"
    const val ADDRESS_LIST_RESPONSE = "address_list_response"
    const val ADDRESS_RESPONSE = "address_response"
    const val ADDRESS_ID = "address_id"
    const val ADDRESS_TYPE = "address_type"

    const val ADDRESS_UPDATE_ACTIVITY = "$ADDRESS/AddressUpdateActivity"

    /**
     * 优惠券
     */
    const val COUPON_PRICE = "coupon_price"
    const val COUPON_ID = "coupon_id"
    const val COUPON_TYPE = "coupon_type"
    const val COUPON_MANAGER_ACTIVITY = "$COUPON/CouponManagerActivity"
    const val COUPON_CHECK_ACTIVITY = "$COUPON/CouponCheckActivity"
    const val COUPON_EXCHANGE_ACTIVITY = "$COUPON/CouponExchangeActivity"
    const val COUPON_SERVICE = "$COUPON$SERVICE/ICouponServiceImpl"

    /**
     * 钱包
     */
    const val WALLET_SERVICE = "$WALLET$SERVICE/MineServiceImpl"
    const val WALLET_ACTIVITY = "$WALLET/WalletActivity"

    /**
     * 钱包
     */
    const val HELP_SERVICE = "$HELP$SERVICE/MineServiceImpl"
    const val HELP_ACTIVITY = "$HELP/HelpActivity"

    /**
     * 设置
     */
    const val SETTING_SERVICE = "$SETTING$SERVICE/MineServiceImpl"

    const val SETTING_TELEPHONE = "setting_telephone"
    const val SETTING_ACTIVITY = "$SETTING/SettingActivity"
    const val SETTING_UPDATE_PHONE_NUM_ACTIVITY = "$SETTING/UpdatePhoneNumActivity"

    const val SETTING_SELF_INFO_ACTIVITY = "$SETTING/SelfInfoActivity"

    const val ABOUT_ACTIVITY = "$SETTING/AboutActivity"

    /**
     * 我的分组
     */
    const val MINE_SERVICE = "$MINE$SERVICE/MineServiceImpl"
    const val MINE_PAGE_FRAGMENT = "$MINE/MinePageFragment"
    const val MINE_HELP_ACTIVITY = "$MINE/HelpActivity"
    const val MINE_FEED_BACK_ACTIVITY = "$MINE/FeedBackActivity"
    const val CUSTOMER_SERVICE_ACTIVITY = "$CUSTOMER/CustomerServiceActivity"

    /**
     * ticket分组
     */


    /**
     * 车次列表
     */
    const val TICKET_TRAIN_LIST_REQUEST = "ticket_train_item_request"
    const val TICKET_TRAIN_IS_STUDENT = "ticket_train_is_student"
    const val TICKET_TRAIN_IS_CHANGE = "ticket_train_is_change"

    const val TICKET_SERVICE = "$TICKET$SERVICE/ITicketServiceImpl"


    const val TICKET_TRAIN_ITEM = "ticket_train_item"
    const val TICKET_TRAIN_DETAIL_ACTIVITY = "$TICKET/TicketTrainDetailActivity"

    const val TICKET_TRAIN_LIST_ACTIVITY = "$TICKET/TrainTicketListActivity"

    const val TICKET_KEY_PASSENGER_LIST = "ticket_key_passenger_list"
    const val KEY_12306_VERIFICATION = "key_12306_verification"

    const val TICKET_UPDATE_PASSENGER_ACTIVITY = "$TICKET/UpdatePassengerActivity"


    /** @Date：1/26/21 改签信息 */


    const val TICKET_KEY_TO_CHANGE_TICKET = "ticket_to_change_ticket"
    const val TICKET_CHANGE_INFO_ACTIVITY = "$TICKET/TicketChangeInfoActivity"
    const val TICKET_KEY_CHANGE_TICKET_REQUEST_BEAN = "ticket_key_change_ticket_request_bean"
    const val TICKET_CHANGE_SELECTED_ACTIVITY = "$TICKET/TicketChangeSelectedActivity"


    /** @Date：1/26/21 车票订单详情 */
    const val TICKET_KEY_SEAT_RESPONSE = "ticket_key_seat_response"
    const val TICKET_ORDER_DETAIL_ACTIVITY = "$TICKET/TicketOrderDetailActivity"
    const val TICKET_KEY_REFUND_ID = "ticket_key_refund_id"
    const val TICKET_REFUND_DETAIL_ACTIVITY = "$TICKET/TicketRefundDetailActivity"

    const val TICKET_ORDER_FAIL_STEP = "ticket_order_fail_step"
    const val TICKET_ORDER_FAIL_REASON = "ticket_order_fail_reason"
    const val TICKET_ORDER_FAIL_STATUS_ACTIVITY = "$TICKET/TicketOrderFailStatusActivity"

    /**手机号码核验 */
    const val TICKET_PHONE_CHECK_ACTIVITY = "$TICKET/TicketPhoneCheckActivity"

    /**1/29/21 高旅服务*/

    const val TICKET_VALUE_LIST = "value_Added_List"
    const val TICKET_SERVICE_RES = "ticket_service_res"
    const val TICKET_GAOLV_SERVICE_ACTIVITY = "$TICKET/TicketGaoLvServiceActivity"


    const val TICKET_TO_INFO_PARAMS = "to_ticket_info_params"
    const val TICKET_INFORMATION_ACTIVITY = "$TICKET/TicketInformationActivity"

    /**
     * 时刻表分组
     */

    const val TIME_TABLE_ACTIVITY = "$TIME/TimeTableActivity"
    const val TRAIN_LIST_ACTIVITY = "$TIME/TrainListActivity"
    const val CAR_NUM_INFO_ACTIVITY = "$TIME/CarNumInfoActivity"
    const val STATION_SELECT_ACTIVITY = "$TIME/StationSelectActivity"

    const val PASSE_PART_SERVICE = "$TIME/PassePartServiceImpl"

    const val TRAIN_LIST_INFO = "train_list_info"
    const val CAR_NUM_INFO = "car_num_info"
    const val STATION_SELECT_INFO = "station_select_info"

    /**
     * 车展大屏分组
     */

    const val SCREEN_TIME_TABLE_ACTIVITY = "$SCREEN/ScreenTableActivity"
    const val SCREEN_STATION_SCREEN_ACTIVITY = "$SCREEN/StationBigScreenActivity"
    const val SCREEN_SCREEN_TRAIN_TIME_INFO_ACTIVITY = "$SCREEN/BigScreenTrainTimeInfoActivity"
    const val SCREEN_SERVICE = "$SCREEN/ScreenService"
    const val SCREEN_TRAIN_CODE = "big_train_code"
    const val SCREEN_TRAIN_DATE = "big_train_date"


    /**
     * 客服分组
     */


    /**
     * 客服分组
     */

    const val SCAN_ACTIVITY = "$SCAN/ScanActivity"

    /**
     * 个人信息
     */

    const val INFO_ACTIVITY = "$INFO/InformationActivity"
    const val INFO_UPDATE_PHONE_NUM_ACTIVITY = "$INFO/UpdatePhoneNumActivity"
    const val INFO_SERVICE = "$INFO$SERVICE/IInfoServiceImpl"
    const val INFO_TELEPHONE = "info_telephone"

    /**
     * 消息信息
     */

    const val MESSAGE_ACTIVITY = "$MESSAGE/MessageActivity"

    const val MESSAGE_SERVICE = "$MESSAGE$SERVICE/IMessageServiceImpl"

    /**
     * login分组
     */

    const val LOGIN_ACTIVITY = "$LOGIN/LoginActivity"
    const val LOGIN_EXTRAS = "extras"
    const val LOGIN_SERVICE = "$LOGIN$SERVICE/ILoginServiceImpl"


    /**
     * login12306分组
     */

    const val LOGIN_12306_SERVICE = "$LOGIN12306$SERVICE/Login12306ServiceImpl"

    const val LOGIN_12306_KEY_USER_NAME = "LOGIN_12306_KEY_USER_NAME"

    const val LOGIN_12306_TO_BEAN = "LOGIN_12306_TO_BEAN"

    const val LOGIN_12306_ACTIVITY = "$LOGIN12306/Login12306Activity"
    const val LOGIN_12306_REGISTER_VERIFY_ACTIVITY = "$LOGIN12306/Register12306VerifyActivity"
    const val LOGIN_12306_ACCOUNT_MANAGER_ACTIVITY = "$LOGIN12306/AccountManager12306Activity"
    const val LOGIN_12306_UPDATE_PASSWORD_VERIFY_ACTIVITY = "$LOGIN12306/UpdatePassword12306VerifyActivity"


    const val LOGIN_12306_UPDATE_PASSWORD_VERIFY_SUCCESS_ACTIVITY = "$LOGIN12306/UpdatePassword12306SuccessActivity"
    const val LOGIN_12306_UPDATE_USER_PHONE = "login_12306_update_user_phone"
    const val LOGIN_12306_UPDATE_USER_PASS_WORD = "login_12306_update_user_pass_word"
    const val LOGIN_12306_REGISTER12306_RESPONSE = "login_12306_register12306_response"
    const val LOGIN_12306_REGISTER_ACTIVITY = "$LOGIN12306/Register12306Activity"


    /**
     * travel行程分组
     */
    const val TRAVEL_SERVICE = "$TRAVEL$SERVICE/ITravelServiceImpl"
    const val TRAVEL_PAGE_FRAGMENT = "$TRAVEL/TravelPageFragment"
    const val TRAVEL_PAGE_ACTIVITY = "$TRAVEL/TravelActivity"
    const val TRAVEL_ADD_ACTIVITY = "$TRAVEL/TravelAddActivity"
    const val TRAVEL_DETAILS_ACTIVITY = "$TRAVEL/TravelDetailsActivity"
    const val TRIP_ID = "trip_Id"

    /**
     * 抢票分组
     */
    const val TICKET_TO_ROB_TICKET = "ticket_to_rob_ticket"
    const val ROB_TICKET_ACTIVITY = "$ROB/RobTicketActivity"
    const val ROB_INFORMATION_ACTIVITY = "$ROB/RobInformationActivity"
    const val ROB_CREATE_TASK_ACTIVITY = "$ROB/RobCreateTaskActivity"
    const val ROB_TRAIN_SELECT_ACTIVITY = "$ROB/RobTrainSelectActivity"
    const val ROB_ROB_VERIFY_TASK = "$ROB/RobVerifyTaskActivity"
    const val ROB_PAY = "$ROB/RobPayActivity"
    const val ROB_TRAIN_SELECT_BEAN = "rob_train_select_bean"
    const val ROB_INFORMATION_DETAIL_BUNDLE = "rob_information_detail_bundle"
    const val ROB_VERIFY_TASK_BUNDLE = "rob_verify_task_bundle"

    //选择车次请求码
    const val ROB_TRAIN_SELECT_REQUEST_CODE = 10

    //选择车次返回数据的key
    const val ROB_TRAIN_ITEM_LIST = "rob_train_item_list"

    //跳转到选择车次页面，默认选中的key
    const val ROB_KEY_TRAIN_ITEM_SELECT = "rob_key_train_item_select"

    /**
     * launcher分组
     */

    /**
     * web分组
     */
    const val WEB_VIEW_ACTIVITY = "$WEB/WebViewActivity"
    const val WEB_PROCOTOL_ACTIVITY = "$WEB/WebProcotolActivity"
    const val WEB_URL = "web_url"
    const val WEB_FROM = "web_from"
    const val WEB_TITLE = "web_title"


    /**
     * protocol分组
     */
    const val PROTOCOL_TITTLE = "protocol_tittle"
    const val PROTOCOL_CONTENT = "protocol_content"
    const val PROTOCOL_ACTIVITY = "$PROTOCOL/ProtocolActivity"

    /**
     * 乘客列表分组
     */
    const val PASSENGER_LIST_ACTIVITY = "$PASSENGER/PassengerListActivity"
    const val KEY_PASSENGER_LIST_BUNDLE = "key_passenger_list_bundle"

    /**
     * 车票订单
     */
    const val TICKET_ORDER_ACTIVITY = "$TICKET_ORDER/TicketOrderActivity"

}