package com.example.android.study.commonres.widget.view

/**
 * 描述：底部筛选框回调
 *
 * @author {Wang Peng} by 1/8/21
 */
interface IFilterCallBack {
    fun onStartTimeSort(boolean: Boolean)
    fun onUseTimeSort(boolean: Boolean)
    fun onPriceSort(boolean: Boolean)
    fun onFilter()
}