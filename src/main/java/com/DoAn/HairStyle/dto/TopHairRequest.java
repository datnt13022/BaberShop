package com.DoAn.HairStyle.dto;

import javax.persistence.*;

public class TopHairRequest {

    private Long id;
    private String image;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
