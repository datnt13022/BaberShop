package com.DoAn.HairStyle.service;

import com.DoAn.HairStyle.dto.*;
import com.DoAn.HairStyle.entity.UserEntity;
import com.DoAn.HairStyle.respositiry.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private JavaMailSender javaMailSender;

    public AuthResponse insertUser(UserEntity user) {
        UserEntity userLogin = userRespository.findByNumberPhone(user.getNumberPhone());
        if ( userLogin == null ) {
        UserEntity userAdd = new UserEntity();
        String token= UUID.randomUUID().toString();
        userAdd.setToken(token);
        userAdd.setRole(user.getRole());
        userAdd.setFullName(user.getFullName());
        userAdd.setNumberPhone(user.getNumberPhone());
        userAdd.setPassWord(user.getPassWord());
        userAdd.setEmail(user.getEmail());
        userRespository.save(userAdd);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setRole(user.getRole());
        authResponse.setToken(token);
        authResponse.setFullname(user.getFullName());
        authResponse.setEmail(userAdd.getEmail());
        authResponse.setStatus("success");
        return authResponse;
        }
        AuthResponse authResponse = new AuthResponse();
        authResponse.setStatus("Số điện thoại đã tồn tại");
        return authResponse;
    }
    public UserEntity getUserByToken(String token) {
        return  userRespository.findByToken(token);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        UserEntity userLogin = userRespository.findByNumberPhone(loginRequest.getNumberPhone());
        if ( userLogin != null ) {
        if (userLogin.getPassWord().equals(loginRequest.getPassWord())){
            AuthResponse authResponse = new AuthResponse();
            authResponse.setToken(userLogin.getToken());
            authResponse.setRole(userLogin.getRole());
            authResponse.setFullname(userLogin.getFullName());
            authResponse.setEmail(userLogin.getEmail());
            authResponse.setStatus("success");
            return authResponse;
        }
        else{
            AuthResponse authResponse = new AuthResponse();
            authResponse.setStatus("Wrong Password!!");
            return authResponse;
        }}
        else{
            AuthResponse authResponse = new AuthResponse();
            authResponse.setStatus("Account not found!!");
            return authResponse;
        }

    }

    public Response deleteByToken(String token) {
        userRespository.deleteByToken(token);
        Response response  = new Response();
        response.setStatus("Success!");
        return response;
    }

    public Response updateUser(String token, UpdateUserRequest updateUserRequest) {
        UserEntity userAdd = new UserEntity();
        userAdd.setToken(token);
        userAdd.setEmail(updateUserRequest.getEmail());
        userAdd.setFullName(updateUserRequest.getFullName());
        userAdd.setRole(updateUserRequest.getRole());
        userAdd.setNumberPhone(updateUserRequest.getNumberPhone());
        userAdd.setPassWord(updateUserRequest.getPassWord());
        userRespository.save(userAdd);
        Response response  = new Response();
        response.setStatus("Success!");
        return response;
    }

    public Response forgotUser(forgotRequest forgotRequest) {

        UserEntity userLogin = userRespository.findByNumberPhone(forgotRequest.getNumberPhone());
        if ( userLogin != null ) {

            if(userLogin.getEmail().equals(forgotRequest.getEmail())){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(userLogin.getEmail());
            msg.setSubject("Mật Khẩu Của Bạn");
            msg.setText("Mật Khẩu của bạn là :"+userLogin.getPassWord());
            javaMailSender.send(msg);
            Response response  = new Response();
            response.setStatus("Mật khẩu của bạn đã được gởi về email!");
            return response;
        }Response response  = new Response();
            response.setStatus("Fail! Email Không Đúng");
            return response;
        }
        Response response  = new Response();
        response.setStatus("Fail! Email Hoặc SDT Không Tồn Tại");
        return response;
    }

    public Response changePassword(changePassword changePassword) {
        UserEntity userLogin = userRespository.findByToken(changePassword.getToken());
        if(userLogin!=null){
            if(userLogin.getPassWord().equals(changePassword.getOldPassword())){
                UserEntity userAdd = new UserEntity();
                userAdd.setToken(userLogin.getToken());
                userAdd.setEmail(userLogin.getEmail());
                userAdd.setFullName(userLogin.getFullName());
                userAdd.setRole(userLogin.getRole());
                userAdd.setNumberPhone(userLogin.getNumberPhone());
                userAdd.setPassWord(changePassword.getNewPassword());
                userRespository.save(userAdd);
                Response response  = new Response();
                response.setStatus("Success! Password Đã Được Thay Đổi ");
                return response;
            }
            Response response  = new Response();
            response.setStatus("Fail! Password Hiện Tại Không Chính xác ");
            return response;
        }
        Response response  = new Response();
        response.setStatus("Fail! Người dùng không tồn tại ");
        return response;
    }
}
