package com.DoAn.HairStyle.dto;


public class UpdateUserRequest {
    private  String fullName;
    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
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


    private  String numberPhone;
    private  String passWord;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    private String email;
}
