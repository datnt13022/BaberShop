package com.DoAn.HairStyle.api;

import com.DoAn.HairStyle.dto.*;
import com.DoAn.HairStyle.entity.OrderEntity;
import com.DoAn.HairStyle.respositiry.OrderRespository;
import com.DoAn.HairStyle.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @Autowired
    private OrderRespository orderRespository;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path ="{token}")
    public List<orderResponse> getAllOrder(@PathVariable("token") String token) {
        return orderService.getAllOrderByToken(token);
    }

    @GetMapping(path ="/booking/{date}")
    public  List<CheckListResponse> getTime(@PathVariable("date") String date) {
        return orderService.checkTime(date);
    }
    @PostMapping(path = "/new/{token}" , produces = "application/json;charset=UTF-8")
    public Response addNewOrder(@Valid @NonNull @RequestBody NewOderResquest request , @PathVariable("token") String token){
        return orderService.addNewOrder(request,token);
    }
    @GetMapping(path ="/today")
    public List<orderResponse> getAllOrderToDay() {
        return orderService.getAllOrderToDay();
    }
    @GetMapping(path ="/{fromDate}/{toDate}")
    public  List<orderResponse> getAllData(@PathVariable("fromDate") String fromDate,@PathVariable("toDate") String toDate ) {
        return orderService.getAllData(fromDate,toDate);
    }
//    @GetMapping(path ="/getNewestBlog")
//    public  List<orderResponse> get3New( ) {
//        return orderService.getAllData(fromDate,toDate);
//    }

    @GetMapping(path = "/cancel/{id}")
public Response cancel(@PathVariable("id") String id){
    return orderService.cancelByID(id);
}
@GetMapping(path = "/checkin/{id}")
public Response checkin(@PathVariable("id") String id){
    return orderService.checkinByID(id);
}
    @GetMapping(path = "/payout/{id}")
    public Response payout(@PathVariable("id") String id){

        return orderService.payoutByID(id);
    }
}
