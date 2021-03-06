package com.DoAn.HairStyle.service;

import com.DoAn.HairStyle.dto.*;
import com.DoAn.HairStyle.entity.*;
import com.DoAn.HairStyle.respositiry.OrderRespository;
import com.DoAn.HairStyle.respositiry.UserRespository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class OrderService {

    @Autowired
    private UserRespository userRespository;
    @Autowired
    private OrderRespository orderRespository;
    private final ServiceService serviceService;
    private final UserService userService;
    private final VoucherService voucherService;

    public OrderService(ServiceService serviceService, UserService userService, VoucherService voucherService) {
        this.serviceService = serviceService;
        this.userService = userService;
        this.voucherService = voucherService;
    }



    public Response addNewOrder(NewOderResquest request, String token) {
        Optional<UserEntity> users = Optional.ofNullable(userRespository.findByToken(token));
        if(request.getVoucherCode()!=null){
        Optional<VoucherEntity> voucher = voucherService.getVoucherByVoucherCode(request.getVoucherCode());
        if (users.orElse(null) != null) {
            if(voucher.orElse(null)!=null) {
                 String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                 Date check =DateTimeFormat.forPattern("dd-MM-yyyy").parseDateTime(localDate).toDate();
//                    Date nowtime = new Date();
//
//                Date check=DateTimeFormat.forPattern("dd-MM-yyyy HH:mm").parseDateTime(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withZone(ZoneId.of("Asia/Ho_Chi_Minh")).format(nowtime.toInstant())).toDate();

                Date begin = voucher.get().getBegin();
                Date end = voucher.get().getEnd();
                if (check.after(begin) && check.before(end)) {
                    UserEntity user = users.get();
                    String dateCheck = request.getDateBooked();
                    String dateStart = dateCheck + " 00:00:00";
                    String dateEnd = dateCheck + " 23:59:00";
                    DateTime dateS = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateStart);
                    DateTime dateE = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateEnd);
//                    List<OrderEntity> list = orderRespository.findAllByTimeBookedBetween(dateS.toDate(), dateE.toDate());
                    List<Date> field1List = orderRespository.findAllByTimeBookedBetween(dateS.toDate(), dateE.toDate()).stream().map(OrderEntity::getTimeBooked).collect(Collectors.toList());
                    List<UserEntity> userBooked = orderRespository.findAllByTimeBookedBetween(dateS.toDate(), dateE.toDate()).stream().map(OrderEntity::getUser).collect(Collectors.toList());
                    List<String> allUserBooked = userBooked.stream().map(UserEntity::getToken).collect(Collectors.toList());
                    SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
                    List<String> timeListBooked = field1List.stream().map(Date -> localDateFormat.format(Date.getTime())).collect(Collectors.toList());
                        if(timeListBooked.contains(request.getTimeBooked())==false){
                            if(allUserBooked.contains(users.get().getToken())==false){
                                if (user.getRole().equals("user")) {
                                    OrderEntity order = new OrderEntity();
                                    order.setStatus(request.getStatus());
                                    Optional<ServiceEntity> service = serviceService.getServiceByID(request.getIdService());
                                    order.setService(service.get());
                                    DateTime date = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm").parseDateTime(request.getDateBooked() + " " + request.getTimeBooked());
                                    DateTime date2 = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm").parseDateTime(request.getDateCreate() + " " + request.getTimeCreate());
                                    order.setTimeBooked(date.toDate());
                                    order.setTimeCreate(date2.toDate());
                                    MathContext mc = new MathContext(2);
                                    BigDecimal total = service.get().getPrice().subtract(voucher.get().getPrice(), mc);
                                    order.setTotalPrice(total);
                                    order.setUser(userService.getUserByToken(token));
                                    order.setVoucher(voucher.get());
                                    orderRespository.save(order);
                                    Response response = new Response();
                                    response.setStatus("Success!");
                                    return response;
                                }
                                Response response = new Response();
                                response.setStatus("You no have permission!");
                                return response;
                            }
                            Response response = new Response();
                            response.setStatus("Bạn chỉ được đặt 1 đơn !");
                            return response;
                        }
                        Response response = new Response();
                        response.setStatus("Giờ của bạn đã được đặt trước, Vui Lòng Đặt Lại!");
                        return response;
                }
                Response response = new Response();
                response.setStatus("Voucher đã hết hạn !");
                return response;
            }
            Response response = new Response();
            response.setStatus("Voucher không tồn tại !");
            return response;
        }
        Response response = new Response();
        response.setStatus("User không tồn tại !");
        return response;
    }
        if (users.orElse(null) != null) {
            UserEntity user = users.get();
            String dateCheck = request.getDateBooked();
            String dateStart = dateCheck + " 00:00:00";
            String dateEnd = dateCheck + " 23:59:00";
            DateTime dateS = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateStart);
            DateTime dateE = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateEnd);
            List<Date> field1List = orderRespository.findAllByTimeBookedBetween(dateS.toDate(), dateE.toDate()).stream().map(OrderEntity::getTimeBooked).collect(Collectors.toList());
            List<UserEntity> userBooked = orderRespository.findAllByTimeBookedBetween(dateS.toDate(), dateE.toDate()).stream().map(OrderEntity::getUser).collect(Collectors.toList());
            List<String> allUserBooked = userBooked.stream().map(UserEntity::getToken).collect(Collectors.toList());
            SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
            List<String> timeListBooked = field1List.stream().map(Date -> localDateFormat.format(Date.getTime())).collect(Collectors.toList());
            if(timeListBooked.contains(request.getTimeBooked())==false){
                if(allUserBooked.contains(users.get().getToken())==false){
                    if (user.getRole().equals("user")) {
                        OrderEntity order = new OrderEntity();
                        order.setStatus(request.getStatus());
                        Optional<ServiceEntity> service = serviceService.getServiceByID(request.getIdService());
                        order.setService(service.get());
                        DateTime date = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm").parseDateTime(request.getDateBooked() + " " + request.getTimeBooked());
                        DateTime date2 = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm").parseDateTime(request.getDateCreate() + " " + request.getTimeCreate());
                        order.setTimeBooked(date.toDate());
                        order.setTimeCreate(date2.toDate());
                        order.setTotalPrice(service.get().getPrice());
                        order.setUser(userService.getUserByToken(token));
                        order.setVoucher(null);
                        orderRespository.save(order);
                        Response response = new Response();
                        response.setStatus("Success!");
                        return response;
                    }
                    Response response = new Response();
                    response.setStatus("You no have permission!");
                    return response;
                }
                Response response = new Response();
                response.setStatus("Bạn chỉ được đặt 1 đơn !");
                return response;
            }
            Response response = new Response();
            response.setStatus("Giờ của bạn đã được đặt trước, Vui Lòng Đặt Lại!");
            return response;
        }
        Response response = new Response();
        response.setStatus("User không tồn tại !");
        return response;
    }


    public List<orderResponse> getAllOrderByToken(String token) {
        UserEntity user = new UserEntity();
        user = userRespository.findByToken(token);
        List<OrderEntity> list= orderRespository.findAllByUser(user);
        List<orderResponse> myList = new ArrayList<>();
        for (final OrderEntity order : list) {
            orderResponse orderResponse = new orderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setFullname(order.getUser().getFullName());
            orderResponse.setService(order.getService());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setVoucher(order.getVoucher());
            orderResponse.setNumberPhone(order.getUser().getNumberPhone());
            orderResponse.setTotalPrice(String.valueOf(order.getTotalPrice()));
            orderResponse.setTimeBooked(formatDate(order.getTimeBooked()));
            orderResponse.setTimeCreate(formatDate(order.getTimeCreate()));
            myList.add(orderResponse);
        }
        return myList;
    }
    @Scheduled(fixedDelay = 30000, initialDelay = 1000 )
    public void checkTimeToCancel(){
        System.out.println("running check");
        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        Date start = new Date();
        String fromDate = dateFormat2.format(start) + " 00:00:00";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date check = new Date();
        String toDate= dateFormat2.format(start)+" "+dateFormat.format(check);
//        Date check=DateTimeFormat.forPattern("dd-MM-yyyy HH:mm").parseDateTime(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").withZone(ZoneId.of("Asia/Ho_Chi_Minh")).format(date.toInstant())).toDate();
        DateTime dateS = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(fromDate) ;
        DateTime dateE = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(toDate) ;
        List<OrderEntity> list = orderRespository.findAllByTimeBookedBetween(dateS.toDate(), dateE.toDate());
        List<OrderEntity> listBeforeFilter= list.stream().filter(i->i.getStatus().equals("booking")).collect(Collectors.toList());
        for (OrderEntity order : listBeforeFilter) {
                updateT(order);
                
        }
    }

    private void updateT(OrderEntity orderToCancel) {
        OrderEntity order = new OrderEntity();
        order.setStatus("cancel");
        order.setService(orderToCancel.getService());
        order.setId(orderToCancel.getId());
        order.setTimeBooked(orderToCancel.getTimeBooked());
        order.setTimeCreate(orderToCancel.getTimeCreate());
        order.setTotalPrice(orderToCancel.getTotalPrice());
        order.setUser(orderToCancel.getUser());
        order.setVoucher(orderToCancel.getVoucher());
        orderRespository.save(order);
    }

    public  List<CheckListResponse> checkTime(String date) {
        String dateStart = date + " 00:00:00";
        String dateEnd = date + " 23:59:00";
        DateTime dateS = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateStart) ;
        DateTime dateE = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateEnd) ;
        List<OrderEntity> list= orderRespository.findAllByTimeBookedBetween(dateS.toDate(),dateE.toDate());
        List<Date> field1List = list.stream().map(OrderEntity::getTimeBooked).collect(Collectors.toList());
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm");
        List<String> timeListBooked = field1List.stream().map(Date -> localDateFormat.format(Date.getTime())).collect(Collectors.toList());
        String[] time = {"08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00"};
        String[] timeByWord = {"8h", "8h30", "9h", "9h30", "10h", "10h30", "11h", "11h30", "12h", "12h30", "13h", "13h30", "14h", "14h30", "15h", "15h30", "16h", "16h30", "17h", "17h30", "18h", "18h30", "19h"};
        List<CheckListResponse> listAbc = new ArrayList<CheckListResponse>();
        Calendar date1 = Calendar.getInstance();
        Date abc = new Date();
        DateFormat datefomat = new SimpleDateFormat("HH:mm");
        datefomat.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
        String dateNow=datefomat.format(abc);
        String[] splitTimeNow = dateNow.split(":");

        date1.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitTimeNow[0]));
        date1.set(Calendar.MINUTE, Integer.parseInt(splitTimeNow[1]));
        date1.set(Calendar.SECOND, 0);
        for (int i = 0; i < time.length; i++) {
            if (timeListBooked.contains(time[i])) {
                CheckListResponse timeInList = new CheckListResponse();
                timeInList.setTime(time[i]);
                timeInList.setTimeByWords(timeByWord[i]);
                timeInList.setFree(false);
                listAbc.add(timeInList);
            } else {

                String localDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                if (date.equals(localDate)) {
                    String reference = time[i];
                    String[] parts = reference.split(":");
                    Calendar date2 = Calendar.getInstance();
                    date2.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[0]));
                    date2.set(Calendar.MINUTE, Integer.parseInt(parts[1]));
                    date2.set(Calendar.SECOND, 0);
                    if (date1.after(date2)) {
                        CheckListResponse timeInList = new CheckListResponse();
                        timeInList.setTime(time[i]);
                        timeInList.setTimeByWords(timeByWord[i]);
                        timeInList.setFree(false);
                        listAbc.add(timeInList);
                    } else {
                        CheckListResponse timeInList = new CheckListResponse();
                        timeInList.setTime(time[i]);
                        timeInList.setTimeByWords(timeByWord[i]);
                        timeInList.setFree(true);
                        listAbc.add(timeInList);
                    }
                }
                else {
                    CheckListResponse timeInList = new CheckListResponse();
                    timeInList.setTime(time[i]);
                    timeInList.setTimeByWords(timeByWord[i]);
                    timeInList.setFree(true);
                    listAbc.add(timeInList);
                }
            }
        }

        return listAbc;
    }

    public List<orderResponse> getAllOrderToDay() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date check = new Date();
        String now=dateFormat.format(check);
        String dateStart = now + " 00:00:00";
        String dateEnd = now + " 23:59:00";
        DateTime dateS = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateStart);
        DateTime dateE = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateEnd);
        List<OrderEntity> list= orderRespository.findAllByTimeBookedBetween(dateS.toDate(),dateE.toDate());
        List<orderResponse> myList = new ArrayList<>();
        for (final OrderEntity order : list) {
            orderResponse orderResponse = new orderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setFullname(order.getUser().getFullName());
            orderResponse.setService(order.getService());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setVoucher(order.getVoucher());
            orderResponse.setNumberPhone(order.getUser().getNumberPhone());
            orderResponse.setTotalPrice(String.valueOf(order.getTotalPrice()));
            orderResponse.setTimeBooked(formatDate(order.getTimeBooked()));
            orderResponse.setTimeCreate(formatDate(order.getTimeCreate()));
            myList.add(orderResponse);
        }
        return myList;
    }
    public String formatDate(Date dateToString){
        DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date = dateFormat2.format(dateToString);
        return date;
    }
    public List<orderResponse> getAllData(String fromDate, String toDate) {
        String dateStart = fromDate + " 00:00:00";
        String dateEnd = toDate + " 23:59:00";
        DateTime dateS = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateStart);
        DateTime dateE = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss").parseDateTime(dateEnd);
        List<OrderEntity> list= orderRespository.findAllByTimeBookedBetween(dateS.toDate(), dateE.toDate());
        List<OrderEntity> listBeforeFilter= list.stream().filter(i->i.getStatus()=="PayoutSuccess").collect(Collectors.toList());
        List<orderResponse> myList = new ArrayList<>();
        for (final OrderEntity order : list) {
            orderResponse orderResponse = new orderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setFullname(order.getUser().getFullName());
            orderResponse.setService(order.getService());
            orderResponse.setStatus(order.getStatus());
            orderResponse.setVoucher(order.getVoucher());
            orderResponse.setNumberPhone(order.getUser().getNumberPhone());
            orderResponse.setTotalPrice(String.valueOf(order.getTotalPrice()));
            orderResponse.setTimeBooked(formatDate(order.getTimeBooked()));
            orderResponse.setTimeCreate(formatDate(order.getTimeCreate()));
            myList.add(orderResponse);
        }
        return myList;

    }
    private void updateCheckin(OrderEntity orderToCancel) {
        OrderEntity order = new OrderEntity();
        order.setStatus("Checkin");
        order.setService(orderToCancel.getService());
        order.setId(orderToCancel.getId());
        order.setTimeBooked(orderToCancel.getTimeBooked());
        order.setTimeCreate(orderToCancel.getTimeCreate());
        order.setTotalPrice(orderToCancel.getTotalPrice());
        order.setUser(orderToCancel.getUser());
        order.setVoucher(orderToCancel.getVoucher());
        orderRespository.save(order);
    }
    private void updatePayout(OrderEntity orderToCancel) {
        OrderEntity order = new OrderEntity();
        order.setStatus("PayoutSuccess");
        order.setService(orderToCancel.getService());
        order.setId(orderToCancel.getId());
        order.setTimeBooked(orderToCancel.getTimeBooked());
        order.setTimeCreate(orderToCancel.getTimeCreate());
        order.setTotalPrice(orderToCancel.getTotalPrice());
        order.setUser(orderToCancel.getUser());
        order.setVoucher(orderToCancel.getVoucher());
        orderRespository.save(order);
    }
    public Response checkinByID(String id) {
        Optional<OrderEntity> order =  orderRespository.findById(Long.valueOf(id));
        updateCheckin(order.get());
        Response response = new Response();
        response.setStatus("Checkin Success!");
        return response;
    }

    public Response payoutByID(String id) {
        Optional<OrderEntity> order =  orderRespository.findById(Long.valueOf(id));
        updatePayout(order.get());
        Response response = new Response();
        response.setStatus("Payout Success!");
        return response;
    }

    public Response cancelByID(String id) {
        Optional<OrderEntity> order =  orderRespository.findById(Long.valueOf(id));
        updateCancel(order.get());
        Response response = new Response();
        response.setStatus("Cancel Success!");
        return response;
    }
    private void updateCancel(OrderEntity orderToCancel) {
        OrderEntity order = new OrderEntity();
        order.setStatus("cancel");
        order.setService(orderToCancel.getService());
        order.setId(orderToCancel.getId());
        order.setTimeBooked(orderToCancel.getTimeBooked());
        order.setTimeCreate(orderToCancel.getTimeCreate());
        order.setTotalPrice(orderToCancel.getTotalPrice());
        order.setUser(orderToCancel.getUser());
        order.setVoucher(orderToCancel.getVoucher());
        orderRespository.save(order);
    }
}
