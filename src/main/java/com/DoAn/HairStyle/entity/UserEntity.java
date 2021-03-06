package com.DoAn.HairStyle.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private  String token;
    @Column
    @NotBlank
    private  String fullName;
    @Column
    @NotBlank
    private  String numberPhone;
    @Column
    @NotBlank
    private  String passWord;
    @Column
    @NotBlank
    private  String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Column
    @NotBlank
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public UserEntity() {
        super();
    }

    public String getToken() {
        return token;
    }

    public String getFullName() {
        return fullName;
    }

    public String getNumberPhone() {
        return numberPhone;
    }



    public String getPassWord() {
        return passWord;
    }
//    @OneToMany(mappedBy="token")
//    List<OrderEntity> order;

}