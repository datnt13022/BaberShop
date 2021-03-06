package com.DoAn.HairStyle.service;

import com.DoAn.HairStyle.dto.Response;
import com.DoAn.HairStyle.dto.ServiceRequest;
import com.DoAn.HairStyle.dto.blogResponse;
import com.DoAn.HairStyle.entity.BlogEntity;
import com.DoAn.HairStyle.entity.ServiceEntity;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.respositiry.ServiceRespository;
import com.DoAn.HairStyle.respositiry.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceService {
    @Autowired
    private ServiceRespository serviceRespository;
    @Autowired
    private UserRespository userRespository;
    public List<ServiceEntity> getAllService() {
//        String[] abc= new String[]{"combo-active","cat-active","uon-active","nhuom-active"};
//        List<String> active = Arrays.asList(abc);
        List<ServiceEntity> listService = new ArrayList<>();
        List<ServiceEntity> list =serviceRespository.findAll();
        for (final ServiceEntity service : list) {
//            if (active.contains(service.getType())) {
                ServiceEntity perSevice = new ServiceEntity();
                perSevice.setImage(service.getImage());
                perSevice.setType(service.getType());
                perSevice.setIdService(service.getIdService());
                perSevice.setDescription(service.getDescription());
                perSevice.setName(service.getName());
                perSevice.setPrice(service.getPrice());
                listService.add(perSevice);
//            }
        }
        return listService;

    }


    public Optional<ServiceEntity> getServiceByID(String id) {
        return serviceRespository.findById(Long.valueOf(id));
    }

    public Response addService(String token, ServiceRequest newService) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                ServiceEntity servicex = new ServiceEntity();
                servicex.setType(newService.getType());
                servicex.setName(newService.getName());
                servicex.setDescription(newService.getDescription());
                servicex.setImage(newService.getImage());
                servicex.setPrice(newService.getPrice());
                serviceRespository.save(servicex);
                Response response = new Response();
                response.setStatus("add service success!");
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

    public Response updateService(String id, String token, ServiceRequest newService) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                ServiceEntity service = new ServiceEntity();
                service.setIdService(Long.valueOf(id));
                service.setType(newService.getType());
                service.setName(newService.getName());
                service.setImage(newService.getImage());
                service.setDescription(newService.getDescription());
                service.setPrice(newService.getPrice());
                serviceRespository.save(service);
                Response response = new Response();
                response.setStatus("update service success!");
                return response;
            }
            Response response = new Response();
            response.setStatus("You no have permission!");
            return response;
        }
        Response response = new Response();
        response.setStatus("Người dùng không tồn tại !");
        return response;
    }
    public Response deleteServiceByID(String token, Long idService) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if (users.orElse(null) != null) {
            if(users.get().getRole().equals("admin")){
                serviceRespository.deleteById(idService);
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

    public List<ServiceEntity> getServiceByType(String type) {
        String typeHighLight=type+"-highlight";
        List<List<ServiceEntity>> lol = Arrays.asList(serviceRespository.findAllByType(type), serviceRespository.findAllByType(typeHighLight));
        List<ServiceEntity> li = lol.stream().collect(ArrayList::new, List::addAll, List::addAll);
        return li;
    }

    public List<ServiceEntity> getAllServiceAdmin() {
        return serviceRespository.findAll();
    }


    public List<ServiceEntity> getHighLightService() {
        String[] listType = {"combo-highlight", "uon-highlight", "nhuom-highlight"};
        List<List<ServiceEntity>> lol = Arrays.asList(serviceRespository.findAllByType(listType[0]), serviceRespository.findAllByType(listType[1]), serviceRespository.findAllByType(listType[2]));
        List<ServiceEntity> li = lol.stream().collect(ArrayList::new, List::addAll, List::addAll);
        return li;
    }
}
