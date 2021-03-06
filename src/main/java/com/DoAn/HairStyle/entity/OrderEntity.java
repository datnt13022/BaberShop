package com.DoAn.HairStyle.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String status;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreate;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeBooked;
    @Column
    private BigDecimal totalPrice;
    @ManyToOne
    @JoinColumn(name = "token" , referencedColumnName = "token")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "voucherCode",referencedColumnName = "voucherCode")
    private VoucherEntity voucher;
    @ManyToOne
    @JoinColumn(name = "idService", referencedColumnName = "idService")
    private ServiceEntity service;

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public ServiceEntity getService() {
        return service;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public void setTimeBooked(Date timeBooked) {
        this.timeBooked = timeBooked;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setVoucher(VoucherEntity voucher) {
        this.voucher = voucher;
    }



    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }


    public Date getTimeCreate() {
        return timeCreate;
    }

    public Date getTimeBooked() {
        return timeBooked;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public UserEntity getUser() {
        return user;
    }

    public VoucherEntity getVoucher() {
        return voucher;
    }


}
