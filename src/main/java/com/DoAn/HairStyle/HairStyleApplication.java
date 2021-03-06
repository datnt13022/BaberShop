package com.DoAn.HairStyle;

import com.DoAn.HairStyle.entity.OrderEntity;
import com.DoAn.HairStyle.respositiry.OrderRespository;
import com.DoAn.HairStyle.service.OrderService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@EnableScheduling
@SpringBootApplication
public class HairStyleApplication {


	private OrderRespository orderRespository;
	@RequestMapping("/")

	@ResponseBody
	String home() {
		 String ip = InetAddress.getLoopbackAddress().getHostAddress();

//		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
//		Date check = new Date();
//		return dateFormat.format(check);
		return ip;
	}



	public static void main(String[] args) {
		SpringApplication.run(HairStyleApplication.class, args);
	}


}
