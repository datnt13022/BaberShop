package com.DoAn.HairStyle.api;

import com.DoAn.HairStyle.dto.NewOderResquest;
import com.DoAn.HairStyle.dto.Response;
import com.DoAn.HairStyle.dto.TopHairRequest;
import com.DoAn.HairStyle.entity.ServiceEntity;
import com.DoAn.HairStyle.entity.TopHairEntity;
import com.DoAn.HairStyle.service.TopHairService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/Tophair")
public class TopHairController {
    private final TopHairService topHairService;

    public TopHairController(TopHairService topHairService) {
        this.topHairService = topHairService;
    }
    @GetMapping
    public List<TopHairEntity> getAllTopHair(){
        return topHairService.getAllTopHair();
    }
    @PostMapping(path = "/insert/{token}")
    public Response addTopHair(@Valid @NonNull @RequestBody TopHairRequest request , @PathVariable("token") String token){
        return topHairService.addTopHair(request,token);
    }
    @PutMapping(path = "/update/{token}")
    public Response updateTopHair(@Valid @NonNull @RequestBody TopHairRequest request , @PathVariable("token") String token){
        return topHairService.updateTopHair(request,token);
    }
    @DeleteMapping(path = "{Token}/delete/{id}")
    public Response deleteTopHair(@PathVariable("Token") String token,@PathVariable("id") Long idTopHair){
        return topHairService.deleteTopHairByID(token, idTopHair);
    }
}
