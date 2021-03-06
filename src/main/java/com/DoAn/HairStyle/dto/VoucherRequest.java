package com.DoAn.HairStyle.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

public class VoucherRequest {

    private String voucherCode;

    private String name;

    private String description;

    private BigDecimal price;
//    @JsonFormat(pattern="MM-dd-yyyy")
    private String begin;
//    @JsonFormat(pattern="MM-dd-yyyy")
    private String end;

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    public String getVoucherCode() {
        return voucherCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getBegin() {
        return begin;
    }

    public String getEnd() {
        return end;
    }
}
