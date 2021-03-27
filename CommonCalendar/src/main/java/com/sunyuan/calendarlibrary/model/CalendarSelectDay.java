package com.sunyuan.calendarlibrary.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * author：six
 * created by:2019-01-20
 * github:https://github.com/sy007
 */
public class CalendarSelectDay<K> implements Serializable {
    private K firstSelectDay;
    private K lastSelectDay;
    private List<K> multiSelectDay = new ArrayList<>(); //multi model use
    private List<K> reservationDay = new ArrayList<>(); //可预约内容显示

    public K getFirstSelectDay() {
        return firstSelectDay;
    }

    public K getLastSelectDay() {
        return lastSelectDay;
    }

    public void setFirstSelectDay(K firstSelectDay) {
        this.firstSelectDay = firstSelectDay;
    }

    public void setLastSelectDay(K lastSelectDay) {
        this.lastSelectDay = lastSelectDay;
    }

    public List<K> getMultiSelectDay() {
        return multiSelectDay;
    }

    public void setMultiSelectDay(List<K> multiSelectDay) {
        this.multiSelectDay = multiSelectDay;
    }

    public List<K> getReservationDay() {
        return reservationDay;
    }

    public void setReservationDay(List<K> reservationDay) {
        this.reservationDay = reservationDay;
    }
}
