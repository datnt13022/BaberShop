package com.DoAn.HairStyle.dto;

import com.DoAn.HairStyle.entity.ServiceEntity;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.entity.VoucherEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class orderResponse {
    private Long id;
    private String status;
    private String timeCreate;
    private String timeBooked;
    private String totalPrice;
    private String fullname;
    private String numberPhone;
    private VoucherEntity voucher;
    private ServiceEntity service;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTimeBooked(String timeBooked) {
        this.timeBooked = timeBooked;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setVoucher(VoucherEntity voucher) {
        this.voucher = voucher;
    }

    public void setService(ServiceEntity service) {
        this.service = service;
    }

    public String getStatus() {
        return status;
    }


    public String getFullname() {
        return fullname;
    }

    public VoucherEntity getVoucher() {
        return voucher;
    }

    public ServiceEntity getService() {
        return service;
    }
}
