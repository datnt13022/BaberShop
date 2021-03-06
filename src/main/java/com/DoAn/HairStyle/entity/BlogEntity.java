package com.DoAn.HairStyle.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "blog")
public class BlogEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long blogID;
    @Column
    private String title;
    @Column
    private String tag;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column
    private String description;
    @Lob
    private String thumbnail;
    @Lob
    private String content;
    @Column
    private String link;


    public UserEntity getUser() {
        return User;
    }

    public void setUser(UserEntity user) {
        User = user;
    }


    @ManyToOne
    @JoinColumn(name = "token", referencedColumnName = "token")
    private UserEntity User;



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



    public void setContent(String content) {
        this.content = content;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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


    public String getContent() {
        return content;
    }

    public String getLink() {
        return link;
    }


}
