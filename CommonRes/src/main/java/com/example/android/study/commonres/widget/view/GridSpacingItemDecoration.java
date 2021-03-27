package com.example.android.study.commonres.widget.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author mo
 */
public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 列数
     */
    private int spanCount;

    /**
     * 间隔
     */
    private int spacing;

    /**
     * 是否包含边缘
     */
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // 这里是关键，需要根据你有几列来判断
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        if (includeEdge) {
            // spacing - column * ((1f / spanCount) * spacing)
            outRect.left = spacing - column * spacing / spanCount;
            // (column + 1) * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing / spanCount;

            // top edge
            if (position < spanCount) {
                outRect.top = spacing;
            }
            // item bottom
            outRect.bottom = spacing;
        } else {
            // column * ((1f / spanCount) * spacing)
            outRect.left = column * spacing / spanCount;
            // spacing - (column + 1) * ((1f / spanCount)
            outRect.right = spacing - (column + 1) * spacing / spanCount;
            // * spacing)
            if (position >= spanCount) {
                // item top
                outRect.top = spacing;
            }
        }
    }
}