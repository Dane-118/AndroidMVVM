package com.sunyuan.calendarlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sunyuan.calendarlibrary.model.CalendarDay;

/**
 * author：six
 * created by:2019-01-20
 * github:https://github.com/sy007
 * @author mo
 */
public class CalendarView extends RecyclerView {

    public CalendarAdapter calendarAdapter;
    private MonthTitleDecoration monthTitleDecoration;

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CalendarView);
        calendarAdapter = new CalendarAdapter(getContext(), typedArray);
        typedArray.recycle();
    }

    /**
     * 释放 MonthTitleDecoration中资源
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // 此处注释 为了在 bottom sheet 重复弹出出现的bug
        // if (monthTitleDecoration != null) {
        // monthTitleDecoration.destroy();
        // }
    }

    /**
     * 外部调用刷新
     */
    public void refresh() {
        calendarAdapter.refresh();
    }

    public int covertToPosition(@NonNull CalendarDay calendarDay) {
        return calendarAdapter.covertToPosition(calendarDay);
    }

    void display(CalendarViewWrapper.CalendarBuilder calendarBuilder) {
        MonthTitleViewCallBack monthTitleViewCallBack = calendarBuilder.monthTitleViewCallBack;
        boolean isStick = calendarBuilder.isStick;
        boolean isShowMonthTitleView = calendarBuilder.isShowMonthTitleView;
        if (isShowMonthTitleView && monthTitleViewCallBack != null) {
            monthTitleDecoration = new MonthTitleDecoration();
            monthTitleDecoration.setStick(isStick);
            monthTitleDecoration.setMonthTitleViewCallBack(monthTitleViewCallBack);
            addItemDecoration(monthTitleDecoration);
        }
        calendarAdapter.init(calendarBuilder);
        setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter(calendarAdapter);
    }

    void notifyView(CalendarViewWrapper.CalendarBuilder calendarBuilder) {
        if (calendarBuilder != null && calendarAdapter != null) {
            calendarAdapter.init(calendarBuilder);
            calendarAdapter.notifyDataSetChanged();
        }
    }
}
