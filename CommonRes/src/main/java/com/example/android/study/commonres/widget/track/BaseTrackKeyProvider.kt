package com.example.android.study.commonres.widget.track

import androidx.recyclerview.selection.ItemKeyProvider

/**
 * 描述：
 *
 * @author JiaoPeng by 3/25/21
 */
class BaseTrackKeyProvider<T> (private val adapter: BaseTrackerAdapter<T>) :
    ItemKeyProvider<T>(SCOPE_CACHED) {

    override fun getKey(position: Int): T = adapter.getItem(position)

    override fun getPosition(key: T): Int = adapter.getPosition(key)

}