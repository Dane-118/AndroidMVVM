package com.sunyuan.calendarlibrary.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: DateVO
 * @Description: 描述
 * @Author: Shao Yang
 * @CreateDate: 2020/4/23 11:05
 */
public class DateVO implements Serializable {
    private String startWeek;
    private String endWeek;
    private String startDateFormat;
    private String endDateFormat;
    private String startDateString;
    private String endDateString;
    private String totalNights;
    private Date startDate;
    private Date endDate;

    public String getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(String startWeek) {
        this.startWeek = startWeek;
    }

    public String getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(String endWeek) {
        this.endWeek = endWeek;
    }

    public String getStartDateFormat() {
        return startDateFormat;
    }

    public void setStartDateFormat(String startDateFormat) {
        this.startDateFormat = startDateFormat;
    }

    public String getEndDateFormat() {
        return endDateFormat;
    }

    public void setEndDateFormat(String endDateFormat) {
        this.endDateFormat = endDateFormat;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public String getEndDateString() {
        return endDateString;
    }

    public void setEndDateString(String endDateString) {
        this.endDateString = endDateString;
    }

    public String getTotalNights() {
        return totalNights;
    }

    public void setTotalNights(String totalNights) {
        this.totalNights = totalNights;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
