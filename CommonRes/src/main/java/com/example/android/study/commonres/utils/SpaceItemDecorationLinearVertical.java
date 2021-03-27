package com.example.android.study.commonres.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecorationLinearVertical extends RecyclerView.ItemDecoration {
    int topSpace;
    int bottomSpace;
    int leftSpace;
    int rightSpace;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //当前位置
        int itemPosition = parent.getChildAdapterPosition(view);
        //ItemView数量
        int childCount = parent.getAdapter().getItemCount();
        if (itemPosition == 0) {
            outRect.bottom = bottomSpace;
        } else if (itemPosition == childCount) {
            outRect.top = topSpace;
        } else {
            outRect.top = topSpace;
            outRect.bottom = bottomSpace;
        }

    }

    public SpaceItemDecorationLinearVertical(int topSpace , int bottomSpace) {
        this.topSpace = topSpace;
        this.bottomSpace = bottomSpace;
    }
}
