package com.DoAn.HairStyle.service;

import com.DoAn.HairStyle.dto.Response;
import com.DoAn.HairStyle.dto.VoucherRequest;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.entity.VoucherEntity;
import com.DoAn.HairStyle.respositiry.UserRespository;
import com.DoAn.HairStyle.respositiry.VoucherRespository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherService {
    @Autowired
    private VoucherRespository voucherRespository;
    @Autowired
    private UserRespository userRespository;

    public  Response addVoucher(String token, VoucherRequest request) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                String dateStart= request.getBegin()+" 00:00:00";
                String dateEnd= request.getEnd()+" 23:59:00";
                DateTime dateS= DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateStart);
                DateTime dateE= DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateEnd);
                VoucherEntity voucher = new VoucherEntity();
                voucher.setName(request.getName());
                voucher.setDescription(request.getDescription());
                voucher.setEnd(dateE.toDate());
                voucher.setBegin(dateS.toDate());
                voucher.setPrice(request.getPrice());
                voucher.setVoucherCode(request.getVoucherCode());
                voucherRespository.save(voucher);
                Response response = new Response();
                response.setStatus("Success!");
                return response;
            }
            Response response = new Response();
            response.setStatus("You no have permission!");
            return response;
        }
        Response response = new Response();
        response.setStatus("Người dùng không tồn tại!");
        return response;
    }
    public Optional<VoucherEntity> getVoucherByVoucherCode(String voucherCode) {
        return voucherRespository.findById(voucherCode);
    }
//    public VoucherEntity checkVoucher(String voucherCo)
    public List<VoucherEntity> getAllVoucher() {
        return voucherRespository.findAll();
    }

    public Response updateVoucher(String token, VoucherRequest request) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                VoucherEntity voucher = new VoucherEntity();
                String dateStart= request.getBegin()+" 00:00:00";
                String dateEnd= request.getEnd()+" 23:59:00";

                DateTime dateS= DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateStart);
                DateTime dateE= DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateEnd);
                voucher.setName(request.getName());
                voucher.setDescription(request.getDescription());
                voucher.setEnd(dateE.toDate());
                voucher.setBegin(dateS.toDate());
                voucher.setPrice(request.getPrice());
                voucher.setVoucherCode(request.getVoucherCode());
                voucherRespository.save(voucher);
                Response response = new Response();
                response.setStatus("Success!");
                return response;
            }
            Response response = new Response();
            response.setStatus("You no have permission!");
            return response;
        }
        Response response = new Response();
        response.setStatus("Người dùng không tồn tại!");
        return response;

    }

    public Response deleteVoucherByID(String token, String voucherCode) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                voucherRespository.deleteById(voucherCode);
                Response response = new Response();
                response.setStatus("Success!");
                return response;
            }
            Response response = new Response();
            response.setStatus("You no have permission!");
            return response;
        }
        Response response = new Response();
        response.setStatus("Người dùng không tồn tại!");
        return response;
    }
}
