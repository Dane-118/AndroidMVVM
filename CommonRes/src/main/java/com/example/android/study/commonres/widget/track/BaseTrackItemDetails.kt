package com.example.android.study.commonres.widget.track

import androidx.recyclerview.selection.ItemDetailsLookup

/**
 * 描述：
 *
 * @author JiaoPeng by 3/25/21
 */
class BaseTrackItemDetails<T> (private val position: Int, private val item: T) :
    ItemDetailsLookup.ItemDetails<T>() {

    override fun getPosition(): Int {
        return position
    }

    override fun getSelectionKey(): T {
        return item
    }

}