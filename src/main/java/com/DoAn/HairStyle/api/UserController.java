package com.DoAn.HairStyle.api;


import com.DoAn.HairStyle.dto.*;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.respositiry.UserRespository;
import com.DoAn.HairStyle.service.UserService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public AuthResponse addUser(@Valid @NonNull @RequestBody UserEntity user){
       return userService.insertUser(user);
    }
    @GetMapping
    public List<UserEntity> getAllUser(){
        return userRespository.findAll();
    }

    @PostMapping(path = "/login")
    public AuthResponse login(@Valid @NonNull @RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
    @GetMapping(path = "{token}")
    public UserEntity getUserByToken(@PathVariable("token") String token){
        return userRespository.findByToken(token);
    }
    @PutMapping(path = "/update/{token}")
    public Response updateUser( @Valid @NonNull @RequestBody UpdateUserRequest updateUserRequest ,@PathVariable("token") String token){
        return userService.updateUser(token,updateUserRequest);
    }
    @DeleteMapping(path = "/delete/{token}")
    public Response deleteUser(@PathVariable("token") String token){
        return userService.deleteByToken(token);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
            }
        };
    }
    @PostMapping(path = "/forgot")
    public Response forgot(@Valid @NonNull @RequestBody forgotRequest forgotRequest){
        return userService.forgotUser(forgotRequest);
    }
    @PostMapping(path = "/changePassword")
    public Response changePassword(@Valid @NonNull @RequestBody changePassword changePassword){
        return userService.changePassword(changePassword);
    }
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.cors();
//    }
}
