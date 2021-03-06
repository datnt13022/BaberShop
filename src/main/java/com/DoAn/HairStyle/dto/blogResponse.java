package com.DoAn.HairStyle.dto;


import java.util.Date;

public class blogResponse {
    private Long blogID;
    private String title;
    private String tag;
    private Date time;
    private String description;
    private String thumbnail;
    private String content;
    private String link;
    private String by;

    public void setBlogID(Long blogID) {
        this.blogID = blogID;
    }

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

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public Long getBlogID() {
        return blogID;
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

    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }

    public String getBy() {
        return by;
    }
}
