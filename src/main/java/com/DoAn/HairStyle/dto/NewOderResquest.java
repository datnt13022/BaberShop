package com.DoAn.HairStyle.dto;

import com.DoAn.HairStyle.entity.ServiceEntity;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.entity.VoucherEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

public class NewOderResquest {
    @NotBlank
    private String status;
    @NotBlank
    private String dateCreate;
    @NotBlank
    private String timeCreate;
    @NotBlank
    private String dateBooked;
    @NotBlank
    private String timeBooked;
    @NotBlank
    private String idService;
    private String voucherCode;

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setDateBooked(String dateBooked) {
        this.dateBooked = dateBooked;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public String getDateBooked() {
        return dateBooked;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public void setTimeBooked(String timeBooked) {
        this.timeBooked = timeBooked;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public String getTimeBooked() {
        return timeBooked;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public String getStatus() {
        return status;
    }


    public String getIdService() {
        return idService;
    }



    public String getVoucherCode() {
        return voucherCode;
    }
}
