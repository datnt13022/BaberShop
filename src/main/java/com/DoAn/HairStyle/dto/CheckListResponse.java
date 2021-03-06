package com.DoAn.HairStyle.dto;

public class  CheckListResponse {
    private String time;
    private String timeByWords;
    private Boolean isFree;

    public void setTime(String time) {
        this.time = time;
    }

    public void setTimeByWords(String timeByWords) {
        this.timeByWords = timeByWords;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public String getTime() {
        return time;
    }

    public String getTimeByWords() {
        return timeByWords;
    }

    public Boolean getFree() {
        return isFree;
    }
}

