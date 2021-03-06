package com.DoAn.HairStyle.api;

import com.DoAn.HairStyle.dto.Response;
import com.DoAn.HairStyle.dto.VoucherRequest;
import com.DoAn.HairStyle.entity.VoucherEntity;
import com.DoAn.HairStyle.service.VoucherService;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/voucher")
public class VoucherController {
    private final VoucherService voucherService;

    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @PostMapping(path ="{token}/add")
    public Response addVoucher(@Valid @NonNull @RequestBody VoucherRequest request,@PathVariable("token") String token){
        return voucherService.addVoucher(token,request);
    }
    @GetMapping(path ="{voucherCode}")
    public Optional<VoucherEntity> getByVoucherCode(@PathVariable("voucherCode") String voucherCode){
        return voucherService.getVoucherByVoucherCode(voucherCode);
    }
    @GetMapping
    public List<VoucherEntity> getAllVoucher(){
        return voucherService.getAllVoucher();
    }
    @PutMapping(path ="{token}/update")
    public Response updateVoucher(@Valid @NonNull @RequestBody VoucherRequest request,@PathVariable("token") String token){
        return voucherService.updateVoucher(token,request);
        }
    @DeleteMapping(path = "{Token}/delete/{id}")
    public Response deleteVoucher(@PathVariable("Token") String token,@PathVariable("id") String voucherCode){
        return voucherService.deleteVoucherByID(token, voucherCode);
    }

}
