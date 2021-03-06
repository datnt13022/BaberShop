package com.DoAn.HairStyle.service;

import com.DoAn.HairStyle.dto.Response;
import com.DoAn.HairStyle.dto.TopHairRequest;
import com.DoAn.HairStyle.entity.TopHairEntity;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.entity.VoucherEntity;
import com.DoAn.HairStyle.respositiry.ServiceRespository;
import com.DoAn.HairStyle.respositiry.TopHairRespository;
import com.DoAn.HairStyle.respositiry.UserRespository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopHairService {
    @Autowired
    private TopHairRespository topHairRespository;
    @Autowired
    private UserRespository userRespository;
    public List<TopHairEntity> getAllTopHair() {
        return topHairRespository.findAll();
    }

    public Response addTopHair(TopHairRequest request, String token) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                TopHairEntity topHair= new TopHairEntity();
                topHair.setImage(request.getImage());
                topHair.setName(request.getName());
                Response response = new Response();
                topHairRespository.save(topHair);
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

    public Response updateTopHair(TopHairRequest request, String token) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                TopHairEntity topHair= new TopHairEntity();
                topHair.setId(request.getId());
                topHair.setImage(request.getImage());
                topHair.setName(request.getName());
                Response response = new Response();
                topHairRespository.save(topHair);
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

    public Response deleteTopHairByID(String token, Long idTopHair) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                topHairRespository.deleteById(idTopHair);
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
