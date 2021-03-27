package com.example.android.study.commonsdk.sensors

/**
 * 描述：
 *
 * @author JiaoPeng by 3/13/21
 */
data class ScBannerClickBean(
        var banner_belong_area: String? = null,
        var banner_rank: Int? = null,
        var page_type: String? = null,
        var banner_name: String? = null,
        var banner_id: Long? = null,
        var city_id: Long? = null,
        var city_name: String? = null,
        var activity_name: String? = null,
        var activity_id: Long? = null,
        var url: String? = null
)