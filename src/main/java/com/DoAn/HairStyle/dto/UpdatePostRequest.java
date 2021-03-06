package com.DoAn.HairStyle.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class UpdatePostRequest {
    private String title;

    private String tag;

    private Date time;

    private String description;

    private String thumbnail;

    private String content;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getTag() {
        return tag;
    }

    public Date getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    private String link;

}
