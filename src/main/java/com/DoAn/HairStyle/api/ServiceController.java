package com.DoAn.HairStyle.api;

import com.DoAn.HairStyle.dto.Response;
import com.DoAn.HairStyle.dto.ServiceRequest;
import com.DoAn.HairStyle.entity.ServiceEntity;
import com.DoAn.HairStyle.service.ServiceService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/service")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }
    @GetMapping
    public List<ServiceEntity> getAllService(){
        return serviceService.getAllService();
    }
    @GetMapping(path = "/all")
    public List<ServiceEntity> getAllServiceAdmin(){
        return serviceService.getAllServiceAdmin();
    }
    @GetMapping(path = "{id}")
    public Optional<ServiceEntity> getServiceByID(@PathVariable("id") String id){
        return serviceService.getServiceByID(id);
    }
    @PostMapping(path = "{token}")
    public Response addService(@Valid @NonNull @RequestBody ServiceRequest service,@PathVariable("token") String token){
        return serviceService.addService(token,service);
    }
    @PutMapping(path = "{Token}/update/{id}")
    public Response updateService(@Valid @NonNull @RequestBody ServiceRequest service,@PathVariable("Token") String token,@PathVariable("id") String id){
        return serviceService.updateService(id,token,service);
    }
    @DeleteMapping(path = "{Token}/delete/{id}")
    public Response deleteService(@PathVariable("Token") String token,@PathVariable("id") String id){
        return serviceService.deleteServiceByID(token, Long.valueOf(id));
    }
    @GetMapping(path = "type/{type}")
    public List<ServiceEntity> getServiceByType(@PathVariable("type") String type){
        return serviceService.getServiceByType(type);
    }


    @GetMapping(path = "/highlightService")
    public List<ServiceEntity> getHighLightService(){
        return serviceService.getHighLightService();
    }

}
