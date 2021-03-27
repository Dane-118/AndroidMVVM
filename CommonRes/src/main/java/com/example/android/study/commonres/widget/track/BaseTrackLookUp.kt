package com.example.android.study.commonres.widget.track

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView

/**
 * 描述：
 *
 * @author JiaoPeng by 3/25/21
 */
class BaseTrackLookUp<T> (private val recyclerView: RecyclerView) :
    ItemDetailsLookup<T>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<T>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as BaseTrackerAdapter<T>.BaseTrackerHolder).itemDetails
        }
        return null
    }

}