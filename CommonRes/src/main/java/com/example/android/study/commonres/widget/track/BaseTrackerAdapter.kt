package com.example.android.study.commonres.widget.track

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.*
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView

/**
 * 描述：
 *
 * @author JiaoPeng by 3/24/21
 */
abstract class BaseTrackerAdapter<T>(
    @LayoutRes private val layoutResId: Int,
    private val data: MutableList<T>
) : RecyclerView.Adapter<BaseTrackerAdapter<T>.BaseTrackerHolder>() {

    var mSelectionTracker: SelectionTracker<T>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseTrackerHolder {
        val layout: View = LayoutInflater.from(parent.context).inflate(layoutResId, parent, false)
        return BaseTrackerHolder(layout)
    }

    override fun onBindViewHolder(holder: BaseTrackerHolder, position: Int) {
        val item = data[position]
        if (item != null && mSelectionTracker != null) {
            mSelectionTracker?.isSelected(item)?.let { bind(holder, item, it) }
            holder.itemView.setOnClickListener {
                if (mSelectionTracker?.selection?.size() == 0) {
                    mSelectionTracker?.select(item)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemId(position: Int): Long {
        return data[position].hashCode().toLong()
    }

    fun getItem(position: Int): T = data[position]

    fun getPosition(value: T): Int = data.indexOf(value)

    protected abstract fun bind(holder: BaseTrackerHolder, item: T, isSelected: Boolean)

    open inner class BaseTrackerHolder(view: View) : RecyclerView.ViewHolder(view) {

        /**
         * 用ID索引视图
         */
        private val views: SparseArray<View> = SparseArray()

        open fun <T : View> getView(@IdRes viewId: Int): T {
            val view = getViewOrNull<T>(viewId)
            checkNotNull(view) { "No view found with id $viewId" }
            return view
        }

        @Suppress("UNCHECKED_CAST")
        open fun <T : View> getViewOrNull(@IdRes viewId: Int): T? {
            val view = views.get(viewId)
            if (view == null) {
                itemView.findViewById<T>(viewId)?.let {
                    views.put(viewId, it)
                    return it
                }
            }
            return view as? T
        }

        val itemDetails: ItemDetailsLookup.ItemDetails<T>
            get() = BaseTrackItemDetails(absoluteAdapterPosition, data[absoluteAdapterPosition])

        open fun setText(@IdRes viewId: Int, value: CharSequence?): BaseTrackerHolder {
            getView<TextView>(viewId).text = value
            return this
        }

        open fun setText(@IdRes viewId: Int, @StringRes strId: Int): BaseTrackerHolder? {
            getView<TextView>(viewId).setText(strId)
            return this
        }

        open fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int): BaseTrackerHolder {
            getView<TextView>(viewId).setTextColor(color)
            return this
        }

        open fun setTextColorRes(@IdRes viewId: Int, @ColorRes colorRes: Int): BaseTrackerHolder {
            getView<TextView>(viewId).setTextColor(itemView.resources.getColor(colorRes))
            return this
        }

        open fun setImageResource(
            @IdRes viewId: Int,
            @DrawableRes imageResId: Int
        ): BaseTrackerHolder {
            getView<ImageView>(viewId).setImageResource(imageResId)
            return this
        }

        open fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable?): BaseTrackerHolder {
            getView<ImageView>(viewId).setImageDrawable(drawable)
            return this
        }

        open fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap?): BaseTrackerHolder {
            getView<ImageView>(viewId).setImageBitmap(bitmap)
            return this
        }

        open fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int): BaseTrackerHolder {
            getView<View>(viewId).setBackgroundColor(color)
            return this
        }

        open fun setBackgroundResource(
            @IdRes viewId: Int,
            @DrawableRes backgroundRes: Int
        ): BaseTrackerHolder {
            getView<View>(viewId).setBackgroundResource(backgroundRes)
            return this
        }

        open fun setVisible(@IdRes viewId: Int, isVisible: Boolean): BaseTrackerHolder {
            val view = getView<View>(viewId)
            view.visibility = if (isVisible) View.VISIBLE else View.INVISIBLE
            return this
        }

        open fun setGone(@IdRes viewId: Int, isGone: Boolean): BaseTrackerHolder {
            val view = getView<View>(viewId)
            view.visibility = if (isGone) View.GONE else View.VISIBLE
            return this
        }

        open fun setEnabled(@IdRes viewId: Int, isEnabled: Boolean): BaseTrackerHolder {
            getView<View>(viewId).isEnabled = isEnabled
            return this
        }

    }
}