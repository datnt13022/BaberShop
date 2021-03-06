package com.DoAn.HairStyle.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "services")
public class ServiceEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idService;
    @Lob
    private String image;
    @Column
    private String description;
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private String type;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdService(Long idService) {
        this.idService = idService;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getIdService() {
        return idService;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
