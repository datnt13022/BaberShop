package com.DoAn.HairStyle.dto;

public class BookingTimeResponse {
    private String time;
    private Boolean isFree;

    public void setTime(String time) {
        this.time = time;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public String getTime() {
        return time;
    }

    public Boolean getFree() {
        return isFree;
    }
}
